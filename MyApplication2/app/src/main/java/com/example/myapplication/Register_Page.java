package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * _This class is a sign up page ___
 * @author __Ece Korkmaz and Cem Apaydın___
 * @version __8-05-2019__
 */

public class Register_Page extends AppCompatActivity {

    //Variables
    private Button buttonSignUpRegister;
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String nameMessage;
    private String passwordMessage;
    private String emailMessage;
    private String userID;
    private boolean stm;

    //Constructors


    //Methods

    public void setStatement( boolean stm)
    {
        this.stm = stm;
    }

    public boolean getStatement()
    {
        return stm;
    }

    /**
     * Sign up a new user to database
     * @param name name of the user
     * @param password password of the user
     * @param eMail eMail of the user
     */

    public boolean newUser(final String eMail, final String name, final String password)
    {

        //Variables

        // Program Code

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mAuth.createUserWithEmailAndPassword( eMail, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful())
                {

                    userID = mAuth.getCurrentUser().getUid();
                    Map<String, Object> user = new HashMap<>();
                    user.put( "Name", name);
                    user.put( "Password", password);
                    user.put( "Email Adress", eMail);
                    DocumentReference ref = fStore.collection( "users").document(userID);
                    ref.set( user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            setStatement(true);
                        }
                    });
                }
            }
        });
        return getStatement();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__page);

        buttonSignUpRegister = findViewById(R.id.button);
        editTextName = findViewById(R.id.editText);
        editTextEmail = findViewById(R.id.editText6);
        editTextPassword = findViewById(R.id.editText2);

        buttonSignUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                nameMessage = editTextName.getText().toString();
                passwordMessage = editTextPassword.getText().toString();
                emailMessage = editTextEmail.getText().toString();

                if(TextUtils.isEmpty(emailMessage)){
                    Toast.makeText(getApplicationContext(),"Please Enter a Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(passwordMessage)){
                    Toast.makeText(getApplicationContext(),"Please Enter a Password",Toast.LENGTH_SHORT).show();
                }
                else if( passwordMessage.length() < 7){
                    Toast.makeText(getApplicationContext(),"Password length must at least 6",Toast.LENGTH_SHORT).show();
                }

                else {

                        if(newUser(emailMessage, nameMessage, passwordMessage)) {
                            Intent register = new Intent(Register_Page.this, MainActivity.class);
                            startActivity(register);
                        }
                    }
                }


        });
    }
}
