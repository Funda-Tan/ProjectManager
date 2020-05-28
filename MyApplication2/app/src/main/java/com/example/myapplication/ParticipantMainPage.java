package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * _This class add a project to database ___
 * @author __Funda Tan___
 * @version __20-05-2019__
 */
public class ParticipantMainPage extends AppCompatActivity {

    //Properties
    private Button addAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_main_page);

        addAssignment = findViewById(R.id.button4);

        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent get = getIntent();

                Intent addAssignmentPage = new Intent( ParticipantMainPage.this, AddAssignment.class);

                addAssignmentPage.putExtra("name", get.getStringExtra("name"));
                addAssignmentPage.putExtra("Project Name", get.getStringExtra("Project Name"));

                startActivity( addAssignmentPage);
            }
        });
    }
}
