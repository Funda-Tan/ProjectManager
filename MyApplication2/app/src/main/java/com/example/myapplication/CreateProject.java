package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateProject extends AppCompatActivity {

    //properties
    private DatabaseStorage strg;
    private EditText projectName;
    private EditText projectDescribtion;
    private EditText projectStartDate;
    private EditText projectEndDate;
    private Button createProjectButton;
    private String name;
    private String startDate;
    private String endDate;
    private String describtion;
    private String inventation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        strg = new DatabaseStorage();

        projectName = findViewById(R.id.editText7);
        projectDescribtion = findViewById(R.id.editText8);
        projectStartDate = findViewById(R.id.editText9);
        projectEndDate = findViewById(R.id.editText10);

        createProjectButton = findViewById(R.id.button7);

        createProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent get = getIntent();

                name = projectName.getText().toString().trim();
                startDate = projectStartDate.getText().toString().trim();
                describtion = projectDescribtion.getText().toString().trim();
                endDate = projectEndDate.getText().toString().trim();

                Intent createProject = new Intent(CreateProject.this, RandomString.class);

                inventation = strg.createProject( name, describtion, startDate, endDate);
                strg.addProjectToUser(inventation, get.getStringExtra( "name"));

                createProject.putExtra( "inventation", inventation);
                createProject.putExtra( "name", name);

                startActivity( createProject);

            }
        });



    }
}
