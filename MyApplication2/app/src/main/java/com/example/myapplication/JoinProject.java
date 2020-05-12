package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinProject extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private String inventationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_project);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        inventationCode = editText.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent projectMainPage = new Intent( JoinProject.this, MainProjectPage.class);
                startActivity(projectMainPage);
            }
        });


    }
}
