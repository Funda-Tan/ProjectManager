package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AssignmentView extends AppCompatActivity {

    //Properties
    private TextView assignmentName;
    private TextView assignmentDescription;
    private TextView assignmentStartDate;
    private TextView assignmentEndDate;
    private TextView assignmentAssignedTo;
    private TextView assignmentAssignedBy;
    private Button back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_view);
    }
}
