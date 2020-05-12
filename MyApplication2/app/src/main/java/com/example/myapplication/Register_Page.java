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

public class Register_Page extends AppCompatActivity {

    //Variables
    private Button buttonSignUpRegister;
    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPassword;
    private DatabaseStorage strg = new DatabaseStorage();
    private String nameMessage;
    private String passwordMessage;
    private String emailMessage;

    //Constructors


    //Methods

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
                else if(passwordMessage.length()<7){
                    Toast.makeText(getApplicationContext(),"Password length must at least 6",Toast.LENGTH_SHORT).show();
                }

                else {
                    if (strg.newUser(emailMessage, nameMessage, passwordMessage)) {
                        Intent register = new Intent(Register_Page.this, MainActivity.class);
                        startActivity(register);
                        strg.addElementToUserNames(nameMessage);
                    }
                }


            }
        });
    }
}
