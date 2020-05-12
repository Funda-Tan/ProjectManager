package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RandomString extends AppCompatActivity {

    //Variables
    private Button toProjectButton;
    private TextView projectCode;
    private String projectName;
    private String projectInventatioCode;
    //Constructors

    //Methods


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_string);

        Intent get = getIntent();
        projectInventatioCode = get.getStringExtra( "inventation");
        projectName = get.getStringExtra( "name");

        toProjectButton = findViewById(R.id.button8);
        projectCode = findViewById(R.id.textView6);

        projectCode.setText( projectInventatioCode);

        toProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                        Intent newIntent = new Intent( RandomString.this, MainProjectPage.class);

                        newIntent.putExtra( "Project name", projectName);
                        startActivity( newIntent);


            }
        });
    }
}
