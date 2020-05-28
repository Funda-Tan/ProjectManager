package com.example.myapplication;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * _This class is not used by main code anymore___
 * @author __Cem Apaydın___
 * @version __9-05-2019__
 */

public class DatabaseStorage {

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseFirestore fStore;

    private String asd;
    private String userID;
    private String currentUserName;
    private String invantationCode;
    private boolean stm;


    //Constructors

    public DatabaseStorage()
    {

        this.stm = false;
        fStore = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


    }
    //Methods




    public void setStatement( boolean stm)
    {
        this.stm = stm;
    }

    public boolean getStatement()
    {
        return stm;
    }

    /*
    *
    *
     */
    public void newUser(final String eMail, final String name, final String password)
    {

        //Variables

        // Program Code

        mAuth.createUserWithEmailAndPassword( eMail, password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful())
                {

                    userID = mAuth.getCurrentUser().getUid();
                    Map<String, Object> user = new HashMap<>();
                    user.put( "Name", name);
                    user.put( "Password", password);
                    user.put( "Email Adress", eMail);
                    user.put( "Project Number", 0);
                    DocumentReference ref = fStore.collection( "users").document(userID);
                    ref.set( user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    });
                }
            }
        });

    }


    /*
    *
    *
     */
    public void setcurrentUserID( String currentUserName)
    {
        this.currentUserName = currentUserName;
    }

    /*
    *
    *
     */
    public String getCurrentUserName()
    {
        return currentUserName;
    }
    /*
    *
    *
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

    /*
    *
    *
     */
    public String createProject( String name, String describtion, String startDate, String endDate)
    {

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

    public String addProjectToUser(final String invantation, String name)
    {

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



    public void setArray( String s)
    {
        this.asd = s;
    }

    public String getArray()
    {

        return asd;
    }

}
