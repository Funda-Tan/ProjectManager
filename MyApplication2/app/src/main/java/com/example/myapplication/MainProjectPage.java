package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainProjectPage extends AppCompatActivity {

    //Properties
    private TextView editTextName;
    private Button assignmentButton;
    private Button participantButton;
    private Button progressionButton;
    private String name;
    private String invitationCode;


    //Methods

    public void define()
    {

        editTextName = findViewById( R.id.textView4);
        participantButton = findViewById( R.id.button6);
        assignmentButton = findViewById(R.id.button2);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_project_page);

        define();

        Intent get = getIntent();
        name = get.getStringExtra("Project Name");
        invitationCode = get.getStringExtra( "ProjectCode");

        editTextName.setText( name);

        participantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( MainProjectPage.this, ParticipantsPage.class);

                intent.putExtra( "name", name);
                intent.putExtra( "ProjectCode", invitationCode);
                startActivity( intent);
            }
        });

        assignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( MainProjectPage.this, AssignmentsProject.class);

                intent.putExtra( "name", name);
                intent.putExtra( "ProjectCode", invitationCode);
                startActivity( intent);
            }
        });

    }
}
