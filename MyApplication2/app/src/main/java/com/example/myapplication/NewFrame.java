package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewFrame extends AppCompatActivity {

    //Variables
    private MainActivity name = new MainActivity();
    private String textName;
    private String userId;
    private TextView nameText;
    private Button createProjectButton;
    private Button yourProjects;
    private Button joinproject;
    private ImageButton accountSettings;
    private EditText projectNameEditText;

    //Constructors

    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_frame);

        createProjectButton = findViewById(R.id.button2);
        yourProjects = findViewById(R.id.button6);
        joinproject = findViewById(R.id.button4);
        accountSettings = findViewById(R.id.imageButton2);
        nameText=findViewById(R.id.textView4);

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


    }
}
