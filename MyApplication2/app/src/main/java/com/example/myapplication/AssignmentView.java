package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
/**
 * _This class show selected assignments properties ___
 * @author __Cem Apaydın___
 * @version __17-05-2019__
 */

public class AssignmentView extends AppCompatActivity {

    //Properties
    private TextView assignmentName;
    private TextView assignmentDescription;
    private TextView assignmentStartDate;
    private TextView assignmentEndDate;
    private TextView assignmentAssignedTo;
    private TextView assignmentAssignedBy;
    private Button back;
    private String assignmentCode;
    private FirebaseDatabase database;
    private FirebaseFirestore fStore;


    /**
     * This method defines all buttons and text views
     */
    public void define()
    {

        assignmentName = findViewById(R.id.textName);
        assignmentDescription = findViewById(R.id.textDescription);
        assignmentStartDate = findViewById(R.id.textStartDate);
        assignmentEndDate = findViewById(R.id.textEndDate);
        assignmentAssignedTo = findViewById(R.id.textAssignTo);

        back = findViewById(R.id.button);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_view);

        fStore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

        Intent get = getIntent();

        define();

        assignmentCode = get.getStringExtra("Assignment Code");


        DocumentReference ref = fStore.collection("Assignments").document(assignmentCode);

        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                assignmentName.setText( documentSnapshot.getString("Name"));
                assignmentDescription.setText( documentSnapshot.getString("Describtion"));
                assignmentStartDate.setText( documentSnapshot.getString("startDate"));
                assignmentEndDate.setText( documentSnapshot.getString("endDate"));
                assignmentAssignedTo.setText( documentSnapshot.getString("Assignments User"));
            }
        });

        assignmentName.setText( assignmentCode);


    }}
