package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * _This class show account settings to user ___
 * @author __Elifnur Alsaç___
 * @version __11-05-2019__
 * @not___ not completed___
 */

public class AccountSettings extends AppCompatActivity {

    //Properties
    private Button resetPassword;
    private Button backTomainMenu;

    //Methods

    public void define()
    {

        resetPassword = findViewById(R.id.button6);
        backTomainMenu = findViewById(R.id.button7);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        define();

        backTomainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toMainMenu = new Intent( AccountSettings.this, NewFrame.class);

                startActivity( toMainMenu);
            }
        });

        /*resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportFragmentManager().beginTransaction()

                        .replace( R.id.fragmentlayout, new ProjectPageFragment1(), "fragment")
                        .setTransitionStyle( FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });

         */
    }
}
