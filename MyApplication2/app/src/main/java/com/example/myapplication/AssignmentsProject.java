package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

/**
 * _This class show assignments of a project ___
 * @author __Cem Apaydın and Onat Korkmaz___
 * @version __19-05-2019__
 */

public class AssignmentsProject extends AppCompatActivity {

    //Porperties
    private String name;
    private String invitationCode;
    private String assignmentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments_project);
        final Intent get = getIntent();

        name = get.getStringExtra("name");
        invitationCode = get.getStringExtra("ProjectCode");

        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();

        final DatabaseReference ref = database.getReference(name + " Assignment");

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

                    final Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    assert map != null;
                    final List<String> valueList = new ArrayList<String>(map.keySet());

                    if( finalI < valueList.size()) {

                        assignmentCode = (String) map.get( valueList.get(finalI));
                        System.out.println( assignmentCode);

                        button.setText(valueList.get(finalI));

                        button.setLayoutParams(params);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent ıntent = new Intent(AssignmentsProject.this, AssignmentView.class);

                                ıntent.putExtra("Project Name", valueList.get(finalI));
                                ıntent.putExtra("Assignment Code", (String) map.get( valueList.get(finalI)));
                                ıntent.putExtra("ProjectCode", get.getStringExtra("name"));

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

                Intent mainPage = new Intent( AssignmentsProject.this, MainProjectPage.class);
                mainPage.putExtra("Project Name", name);
                mainPage.putExtra("ProjectCode", invitationCode);

                startActivity(mainPage);
            }
        });
        backButton.setBackground( this.getResources().getDrawable( R.drawable.backcuttonbackground));
        linearLayout.addView(backButton);


    }
}

