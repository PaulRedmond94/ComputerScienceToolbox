package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

import static android.database.DatabaseUtils.dumpCursorToString;

public class AsciiTable extends AppCompatActivity {

    Cursor myCursor;
    MyDBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascii_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setup home button
        //Reference: The following line of code is from: http://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
        toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);
        //Reference complete

        //set up db
        db = new MyDBManager(this);
        try{
            db.open();
            myCursor=db.getAllItems();

        }catch(Exception e){
            Toast.makeText(this, "error trying to open database",Toast.LENGTH_SHORT);

        }

        //onClickListener for returning to the home page
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            Intent intent;
            @Override
            public void onClick(View v){
                //change activity code
                //intent = new Intent(MainActivity.this, NumberBaseConvertor.class);
                intent = new Intent(AsciiTable.this, MainActivity.class);
                startActivity(intent);
                finish();
            }//end on click

        });//end onclicklistener

    }//end onCreate

    /* Old method used to check the contents of the ascii Table
    public void asciiTest (View v){
        myCursor = db.getAllItems();
        TextView temp = (TextView)findViewById(R.id.txtMyStuff);

        temp.setText(dumpCursorToString(myCursor));
        temp.setMovementMethod(new ScrollingMovementMethod());

        Log.e("error", "Cursor contents" + dumpCursorToString(myCursor));


        //temp1 = myCursor.getString(myCursor.getColumnIndex(""));
        //temp2 = myCursor.getString(1);
        //temp3 = myCursor.getString(2);
        //temp4 = myCursor.getString(5);

        //Toast.makeText(this,temp1 + " " + temp2 + " " + temp3 + " " + temp4,Toast.LENGTH_SHORT).show();


    }*/

    public void checkAsciiTable(View v){
        Toast.makeText(this,"Check ascii table is working",Toast.LENGTH_SHORT).show();

    }//end checkAsciiTable

    public void viewAsciiTable(View v){
        Toast.makeText(this,"View full ascii table is working", Toast.LENGTH_SHORT).show();

    }//end viewAsciiTable

}
