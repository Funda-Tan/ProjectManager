package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

/**
 * _This class add a project to database ___
 * @author __Elifnur Alsaç and Onat Korkmaz___
 * @version __17-05-2019__
 */

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
    private String invitation;
    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseFirestore fStore;


    //Methods

    /**
     * add user's unique code to project in database
     * @param name name of the user
     * @param projectCode assignment's unique code for add it to user in database
     */
    public void addUserToProject( String name, String projectCode)
    {

        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        final DatabaseReference ref;
        final DatabaseReference[] ref1 = new DatabaseReference[1];
        DocumentReference ref2;

        userID = mAuth.getCurrentUser().getUid();

        ref2 = fStore.collection( "users").document(userID);

        ref = database.getReference(projectCode);

        ref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ref1[0] = ref.child( documentSnapshot.getString("Name"));

                ref1[0].setValue( userID);
            }
        });


    }


    /**
     * create a random string for assignment's unique code
     * @return  a random string with 10 character length
     */
    public String RandomString() {

        String charSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";


        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            int index = (int)(charSequence.length() * Math.random());

            sb.append(charSequence.charAt(index));
        }

        return sb.toString();
    }

    /**
     * add assignment's unique code to user in database
     * @param name name of the project
     * @param describtion describtion of the project
     * @param startDate start date of the project
     * @param endDate end date of the project
     */
    public String createProject( String name, String describtion, String startDate, String endDate)
    {

        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        String inventation;

        inventation = RandomString();

        Map<String, Object> project = new HashMap<>();
        project.put( "Name", name);
        project.put( "Describtion", describtion);
        project.put( "startDate", startDate);
        project.put( "endDate", endDate);
        project.put( "ProjectCode", inventation);

        DocumentReference ref = fStore.collection( "Projects").document(inventation);
        ref.set( project).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });


        return inventation;

    }

    /**
     * add assignment's unique code to user in database
     * @param name name of the project
     * @param invitation project's unique code for add it in database
     */
    public String addProjectToUser(final String invitation, String name)
    {

        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        final DatabaseReference ref;
        final DatabaseReference[] ref1 = new DatabaseReference[1];
        DocumentReference ref2;
        Map<String, Object> user = new HashMap<>();
        final String[] key = new String[1];

        userID = mAuth.getCurrentUser().getUid();
        ref2 = fStore.collection( "Projects").document(invitation);

        ref = database.getReference(name);

        ref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ref1[0] = ref.child( "Project " + documentSnapshot.getString("Name"));

                ref1[0].setValue( invitation);
            }
        });


        return invitation;
    }

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

                invitation = createProject( name, describtion, startDate, endDate);
                addProjectToUser(invitation, get.getStringExtra( "name"));
                addUserToProject( get.getStringExtra( "name"), invitation);

                createProject.putExtra( "inventation", invitation);
                createProject.putExtra( "Project Name", name);

                startActivity( createProject);

            }
        });



    }
}
