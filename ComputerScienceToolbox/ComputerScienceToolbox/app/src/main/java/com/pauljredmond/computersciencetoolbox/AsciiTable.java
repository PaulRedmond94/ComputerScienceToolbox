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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.database.DatabaseUtils.dumpCursorToString;

public class AsciiTable extends AppCompatActivity {

    //create database objects
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

    //function for searching database for valid record
    public void checkAsciiTable(View v){
        //Load RadioButtons for verification purposes
        RadioButton radBtnDecimal, radBtnCharacter;
        radBtnDecimal = (RadioButton) findViewById(R.id.radBtnDecimalAscii);
        radBtnCharacter = (RadioButton) findViewById(R.id.radBtnCharacterAscii);

        //loadEditText
        EditText txtUserInput = (EditText) findViewById(R.id.txtUserInputAscii);

        //Load and clear output section
        TextView txtAsciiOutput = (TextView) findViewById(R.id.txtAsciiOutput);
        txtAsciiOutput.setText("");

        //assign db
        MyDBManager db = new MyDBManager(this);

        //hide the keyboard when the button is pressed
        //Reference: Following code taken from: http://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
        InputMethodManager inputManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        //reference ends here

        //validate the user has entered something OR check if user entered an input that is too long
        if(txtUserInput.getText().length()<1||txtUserInput.getText().length()>3){
            Toast.makeText(this,"Error, input must between 1 and 3 characters in Length!",Toast.LENGTH_SHORT).show();

        }//end if
        else{
            //verify that a radio button is checked
            if(!radBtnDecimal.isChecked() && !radBtnCharacter.isChecked()){
                Toast.makeText(this,"Error, you never selected an input type!", Toast.LENGTH_SHORT).show();

            }//end if

            //try to avoid crashing from database error
            try{
                //open database
                db.open();

                //if user checked the decimal radio button
                if(radBtnDecimal.isChecked()){
                    //parse input
                    try{
                        //grab input
                        int userInput;
                        userInput = Integer.parseInt(txtUserInput.getText().toString());

                        Cursor databaseReturnVal;
                        databaseReturnVal = db.getItem(userInput);

                        if(databaseReturnVal == null){
                            Toast.makeText(this,"Error, no result found!",Toast.LENGTH_SHORT).show();

                        }//end if
                        else{
                            if(databaseReturnVal.getCount()>1||databaseReturnVal.getCount()<1){
                                Toast.makeText(this, "Error, no results found!",Toast.LENGTH_SHORT).show();

                            }//end if
                            else{
                                txtAsciiOutput.setText("Decimal Value : " + databaseReturnVal.getInt(1) + "\nCharacter Value : " + databaseReturnVal.getString(2));

                            }//end else
                           //txtAsciiOutput.setText(dumpCursorToString(databaseReturnVal));

                        }//end else

                    }catch(NumberFormatException nfe){
                        Toast.makeText(this, "Error you didn't enter a valid number!",Toast.LENGTH_SHORT).show();

                    }//end catch

                }//end if radBtnDecimal is checked

                else if(radBtnCharacter.isChecked()){

                    String userInput = txtUserInput.getText().toString();

                    Cursor databaseReturnVal;
                    Toast.makeText(this,userInput,Toast.LENGTH_SHORT).show();
                    //Verify that no more than one character was entered
                    if(userInput.length()!= 1){
                        Toast.makeText(this, "Error, You must enter an input that is one character in length!", Toast.LENGTH_SHORT).show();

                    }//end if
                    else{
                        databaseReturnVal = db.getItem(userInput);

                        if(databaseReturnVal == null){
                            Toast.makeText(this,"Error, no result found!",Toast.LENGTH_SHORT).show();

                        }//end if
                        else{
                            if(databaseReturnVal.getCount()>1||databaseReturnVal.getCount()<1){
                                Toast.makeText(this, "Error, your input returned more than one value. Could you try being more specific?",Toast.LENGTH_SHORT).show();

                            }//end if
                            else{
                                txtAsciiOutput.setText("Decimal Value : " + databaseReturnVal.getInt(1) + "\nCharacter Value : " + databaseReturnVal.getString(2));

                            }//end else
                            //txtAsciiOutput.setText(dumpCursorToString(databaseReturnVal));

                        }//end else

                    }

                }//end else if radBtncharacter is checked

            }catch(Exception e){
                Toast.makeText(this, "You have encountered a database error!",Toast.LENGTH_SHORT).show();

            }finally{
                db.close();

            }//end try catch finally

        }//end else

    }//end checkAsciiTable

    //function for viewing the full database
    public void viewAsciiTable(View v){
        Intent myIntent;
        myIntent = new Intent(AsciiTable.this,ShowFullAsciiTable.class);
        startActivity(myIntent);

    }//end viewAsciiTable

}
