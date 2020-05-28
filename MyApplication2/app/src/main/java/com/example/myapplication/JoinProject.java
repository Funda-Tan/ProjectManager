package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * _This class add a project to database ___
 * @author __Cem Apaydın___
 * @version __11-05-2019__
 */
public class JoinProject extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private String inventationCode;
    private String userID;
    private String userName;
    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    //Methods

    /**
     * add project's unique code to user in database
     * @param name name of the user
     * @param invantation assignment's unique code for add it to user in database
     */
    public String addProjectToUser(final String invantation, String name)
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
        ref2 = fStore.collection( "Projects").document(invantation);

        ref = database.getReference(name);

        ref2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ref1[0] = ref.child( "Project " + documentSnapshot.getString("Name"));

                ref1[0].setValue( invantation);
            }
        });


        return invantation;
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_project);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        Intent get = getIntent();

        userName = get.getStringExtra("name");

        inventationCode = editText.getText().toString();

        final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inventationCode = editText.getText().toString();

                firebaseFirestore.collection("Projects").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Log.d("data", inventationCode);

                                if ( inventationCode.equals(document.getId()))
                                {

                                    Intent projectMainPage = new Intent( JoinProject.this, MainProjectPage.class);
                                    projectMainPage.putExtra( "ProjectCode", inventationCode);

                                    addUserToProject( userName, inventationCode);
                                    addProjectToUser( inventationCode, userName);

                                    startActivity(projectMainPage);
                                }

                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Your invitation code is not valid",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });


            }
        });


    }
}
