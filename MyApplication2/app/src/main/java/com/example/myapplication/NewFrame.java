package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class NewFrame extends AppCompatActivity {

    //Variables
    private String textName;
    private String userId;
    private TextView nameText;
    private Button createProjectButton;
    private Button yourProjects;
    private Button joinproject;
    private Button signOut;
    private ImageButton accountSettings;
    private FirebaseAuth mAuth;

    //Constructors

    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_frame);

        createProjectButton = findViewById(R.id.button2);
        yourProjects = findViewById(R.id.button6);
        joinproject = findViewById(R.id.button4);
        signOut = findViewById(R.id.button7);
        accountSettings = findViewById(R.id.imageButton2);
        nameText=findViewById(R.id.textView4);

        mAuth = FirebaseAuth.getInstance();

        Intent get = getIntent();
        textName = get.getStringExtra("Input");
        userId = get.getStringExtra("userID");

        nameText.setText( textName);

        createProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent createProjectPage = new Intent(NewFrame.this, CreateProject.class);
                createProjectPage.putExtra( "name", textName);

                startActivity(createProjectPage);
            }
        });

        accountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent accountSeetingsPage = new Intent( NewFrame.this, AccountSettings.class);

                startActivity( accountSeetingsPage);

            }
        });

        yourProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent yourProjectsFrame = new Intent( NewFrame.this, YourProjects.class);
                yourProjectsFrame.putExtra( "name", textName);
                startActivity( yourProjectsFrame);
            }
        });

        joinproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joinProjectFrame = new Intent( NewFrame.this, JoinProject.class);
                joinProjectFrame.putExtra( "name", textName);
                startActivity( joinProjectFrame);
            }
        });


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();

                Intent backToMainFrame = new Intent( NewFrame.this, MainActivity.class);
                startActivity( backToMainFrame);
            }
        });

    }
}
