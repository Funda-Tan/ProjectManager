package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ParticipantsPage extends AppCompatActivity {

    //Properties
    private String name;
    private String invitationCode;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_page);

        final Intent get = getIntent();

        name = get.getStringExtra("Project name");
        invitationCode = get.getStringExtra("ProjectCode");

        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference(invitationCode);

        final LinearLayout linearLayout = findViewById(R.id.LinearLayout);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(

                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins( 100, 50, 100, 0);

        for (int i = 0; i < 20; i++) {

            final Button button = new Button(this);
            button.setBackground( this.getResources().getDrawable( R.drawable.buttunbackground));


            final int finalI = i;
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    assert map != null;
                    final List<String> valueList = new ArrayList<String>(map.keySet());

                    if( finalI < valueList.size()) {

                        button.setText(valueList.get(finalI));
                        System.out.println(valueList.get(finalI));
                        button.setLayoutParams(params);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent ıntent = new Intent(ParticipantsPage.this, ParticipantMainPage.class);
                                ıntent.putExtra("name", valueList.get(finalI));
                                ıntent.putExtra("Project Name", get.getStringExtra("name"));

                                startActivity(ıntent);
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
        Button backButton = new Button(this);
        backButton.setText( "Back");
        backButton.setLayoutParams(params);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainPage = new Intent( ParticipantsPage.this, MainProjectPage.class);

                startActivity(mainPage);
            }
        });
        backButton.setBackground( this.getResources().getDrawable( R.drawable.backcuttonbackground));
        linearLayout.addView(backButton);


    }

    }

