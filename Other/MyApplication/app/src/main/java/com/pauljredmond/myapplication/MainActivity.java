package com.pauljredmond.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final static String My_TAG = "LifeCycleTag";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(My_TAG, "onCreate executes ...");
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i(My_TAG, "onRestart executes ...");
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(My_TAG, "onStart executes ...");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(My_TAG, "onResume executes ...");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(My_TAG, "onPause executes");

    }
    protected void onStop(){
        super.onStop();
        Log.i(My_TAG, "onStop executes");

    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(My_TAG, "onDestroy executes");

    }

// Etc…. For remaining lifecycle methods

}
