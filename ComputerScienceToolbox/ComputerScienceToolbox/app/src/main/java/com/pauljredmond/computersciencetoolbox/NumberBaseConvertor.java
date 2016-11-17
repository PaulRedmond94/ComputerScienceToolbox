package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class NumberBaseConvertor extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //used for setting up home button
        setContentView(R.layout.activity_number_base_convertor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup home button
        //Reference: The following line of code is from: http://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
        toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);
        //Reference complete

        //onClickListener for returning to the home page
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            Intent intent;
            @Override
            public void onClick(View v){
                //change activity code
                //intent = new Intent(MainActivity.this, NumberBaseConvertor.class);
                intent = new Intent(NumberBaseConvertor.this, MainActivity.class);
                startActivity(intent);
                finish();
            }//end on click

        });//end onclicklistener

    }//end onCreate

}//end class
