package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Variables
    private Button buttonSignUp;
    private Button buttonSignIn;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private DatabaseStorage strg = new DatabaseStorage();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> nameList = new ArrayList<String>(Arrays.asList("Funda Tan", "Elifnur Alsac", "Onat Korkmaz", "Cem Apaydin", "Ece Korkmaz"));
    private ArrayList<String> passwordList = new ArrayList<String>(Arrays.asList("123", "123", "123", "123", "123"));
    private String userName;
    private String password;
    private String userID;
    private DatabaseStorage databaseStorage = new DatabaseStorage();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //Constructors

    public MainActivity() {

    }


    /*
     *
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonSignUp = findViewById(R.id.button5);
        this.buttonSignIn = findViewById(R.id.button3);
        this.editTextUserName = findViewById(R.id.editText3);
        this.editTextPassword = findViewById(R.id.editText4);


        Intent get = getIntent();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent register = new Intent(MainActivity.this, Register_Page.class);
                startActivity(register);


            }
        });


        buttonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                userName = editTextUserName.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();

                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(userName, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {


                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    final FirebaseUser currentUser = mAuth.getCurrentUser();

                                    userID = currentUser.getUid();
                                    strg.setcurrentUserID(userID);

                                    DocumentReference databaseRef = db.collection( "users").document( userID);

                                    databaseRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                                            Intent MainMenu = new Intent(MainActivity.this, NewFrame.class);
                                            MainMenu.putExtra("Input", documentSnapshot.getString( "Name"));
                                            MainMenu.putExtra( "UserID", userID);
                                            startActivity(MainMenu);

                                        }
                                    });



                                }

                                else
                                    {

                                        Toast.makeText(getApplicationContext(),"Your informations not valid",Toast.LENGTH_SHORT).show();

                                    }
                            }
                        });

            }
        });
        }
    }
