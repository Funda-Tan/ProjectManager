package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAssignment extends AppCompatActivity {
    //Properties
    private EditText assignmentName;
    private EditText assignmentDescription;
    private EditText assignmentEndDate;
    private EditText assignmentStartDate;
    private Button addAssignment;
    private String assignmentCode;
    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    //Methods

    public void addAssignmentToProject(final String name, String projectCode, String assignmentCode1)
    {

        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        final DatabaseReference ref;
        final DatabaseReference[] ref1 = new DatabaseReference[1];

        ref = database.getReference(projectCode + " Assignment");


                ref1[0] = ref.child( name);

                ref1[0].setValue( assignmentCode1);



    }

    public void addAssignmentToUser(final String name, String userName, String assignmentCode1)
    {

        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        final DatabaseReference ref;
        final DatabaseReference[] ref1 = new DatabaseReference[1];

        ref = database.getReference(userName + " Assignment");


        ref1[0] = ref.child( name);

        ref1[0].setValue( assignmentCode1);



    }

    public String RandomString() {

        String charSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";


        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            int index = (int)(charSequence.length() * Math.random());

            sb.append(charSequence.charAt(index));
        }

        return sb.toString();
    }

    public void define()
    {

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        Intent get = getIntent();

        assignmentCode = RandomString();

        assignmentName = findViewById(R.id.editText7);
        assignmentDescription = findViewById(R.id.editText8);
        assignmentEndDate = findViewById(R.id.editText10);
        assignmentStartDate = findViewById(R.id.editText9);

        Map<String, Object> project = new HashMap<>();
        project.put( "Name", assignmentName.getText().toString());
        project.put( "Describtion", assignmentDescription.getText().toString());
        project.put( "startDate", assignmentStartDate.getText().toString());
        project.put( "endDate", assignmentEndDate.getText().toString());
        project.put( "Assignments Code", assignmentCode);
        project.put( "Assignments Project", get.getStringExtra("Project Name"));
        project.put( "Assignments User", get.getStringExtra("name"));

        addAssignmentToProject( assignmentName.getText().toString(), get.getStringExtra("Project Name"), assignmentCode);

        addAssignmentToUser(assignmentName.getText().toString(), get.getStringExtra("name"), assignmentCode);

        DocumentReference ref = fStore.collection( "Assignments").document(assignmentCode);
        ref.set( project).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        addAssignment = findViewById(R.id.button7);

        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backToMainPage = new Intent( AddAssignment.this, ParticipantMainPage.class);

                define();

                startActivity(backToMainPage);
            }
        });

    }
}
