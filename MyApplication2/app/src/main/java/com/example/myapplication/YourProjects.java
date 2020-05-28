package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * _This class show projects of a user  ___
 * @author __Cem Apaydın___
 * @version __10-05-2019__
 */

public class YourProjects extends AppCompatActivity {

    //Properties
    String userID;
    ArrayList<String> projects = new ArrayList<String>();
    ArrayList<String> list = new ArrayList<String>();
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_projects);
        Intent get = getIntent();

        name = get.getStringExtra("name");


        final LinearLayout linearLayout = findViewById(R.id.LinearLayout);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        userID = mAuth.getCurrentUser().getUid();

        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();



        for (int i = 0; i < 20; i++) {

            DatabaseReference ref = database.getReference(name);

            final Button button = new Button(this);
            button.setBackground( this.getResources().getDrawable( R.drawable.buttunbackground));
            final Intent projectFrame = new Intent( YourProjects.this, MainProjectPage.class);
            final int finalI = i;

            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    final List<String> valueList = new ArrayList<String>(map.keySet());
                    List<String> valueList2 = new ArrayList<String>(Collections.singleton(String.valueOf(map.values())));

                    final String invitaitionCode;

                    if (finalI < valueList.size()) {


                        invitaitionCode = (String) map.get( valueList.get(finalI));

                        button.setText( valueList.get(finalI));

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(

                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        params.setMargins( 100, 50, 100, 0);

                        button.setLayoutParams(params);

                        button.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                projectFrame.putExtra( "ProjectCode", invitaitionCode);
                                projectFrame.putExtra( "Project Name", valueList.get(finalI));
                                startActivity( projectFrame);

                            }
                        });
                        if(linearLayout.getParent() == null) {

                            ((ViewGroup)linearLayout.getParent()).removeView(linearLayout);

                        }

                        linearLayout.addView(button);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        final Intent backToMainMenu = new Intent( YourProjects.this, NewFrame.class);



        Button backButton = new Button(this);
        backButton.setText( "Back to Menu");
        backButton.setBackground( this.getResources().getDrawable( R.drawable.backcuttonbackground));
        linearLayout.addView(backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity( backToMainMenu);
            }
        });
    }

}

