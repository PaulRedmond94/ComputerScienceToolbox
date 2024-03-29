package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChangeAsciiTable extends AppCompatActivity {

    //create Database Object
    MyDBManager db;
    Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ascii_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //display a notification to the user but only once
        if(MainActivity.getCookie()==0){
            Toast.makeText(this, "Enter details for what you wish to delete or update in the ascii table.", Toast.LENGTH_LONG).show();
            MainActivity.setCookieVal();

        }//end if

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
                intent = new Intent(ChangeAsciiTable.this, MainActivity.class);
                startActivity(intent);
                finish();
            }//end on click

        });//end onclicklistener


        //set up db
        db = new MyDBManager(this);
        try{
            db.open();
            myCursor=db.getAllItems();

        }catch(Exception e){
            Toast.makeText(this, "error trying to open database",Toast.LENGTH_SHORT).show();

        }

    }

    public void executeQuery(View v){

        //hide the keyboard when the button is pressed
        //Reference: Following code taken from: http://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
        InputMethodManager inputManager = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        //reference ends here

        //get variables
        RadioButton updateRadBtn = (RadioButton) findViewById(R.id.radBtnChangeAsciiUpdate);
        RadioButton deleteRadBtn = (RadioButton) findViewById(R.id.radBtnChangeAsciiDelete);

        EditText userInputChar = (EditText) findViewById(R.id.changeAsciiCharcter);
        EditText userInputDec = (EditText) findViewById(R.id.changeAsciiDecimal);

        //check which radioButton is checked
        if(updateRadBtn.isChecked()){
            //verify if inputs are valid
            if(userInputChar.getText().toString().length()!=1){
                Toast.makeText(this, "Error, your character input must be at least one character in length!", Toast.LENGTH_SHORT).show();

            }//end if

            //verify input length
            else if(userInputDec.getText().toString().length()<=0 || userInputDec.getText().toString().length()>3){
                Toast.makeText(this, "Error, your decimal input must be between one and three characters in length!", Toast.LENGTH_SHORT).show();

            }
            else{
                //inputs are at least valid in length now
                //verify input is a valid long
                try{
                    long userInputDecVal = Long.parseLong(userInputDec.getText().toString());
                    String userInputCharVal = userInputChar.getText().toString();

                    if(db.updateItem(userInputDecVal,userInputCharVal)){
                        Toast.makeText(this,"Update Successful!",Toast.LENGTH_SHORT).show();
                        userInputChar.setText("");
                        userInputDec.setText("");

                    }
                    else{
                        Toast.makeText(this,"Update Unsuccessful!",Toast.LENGTH_SHORT).show();

                    }

                }catch(NumberFormatException nfe){
                    Toast.makeText(this, "Error, the number you entered was invalid!", Toast.LENGTH_SHORT).show();

                }//end try/catch

            }//end else

        }//end if update is checked
        else if(deleteRadBtn.isChecked()){
            //verify length of input
            if(userInputDec.getText().toString().length()<=0 || userInputDec.getText().toString().length()>3){
                Toast.makeText(this, "Error, your decimal input must be between one and three characters in length!", Toast.LENGTH_SHORT).show();

            }
            else{
                //verify input is length
                try{
                    long userInputDecVal = Long.parseLong(userInputDec.getText().toString());

                    if(db.deleteItem(userInputDecVal)){
                        Toast.makeText(this,"Delete Successful!",Toast.LENGTH_SHORT).show();
                        userInputChar.setText("");
                        userInputDec.setText("");
                    }
                    else{
                        Toast.makeText(this,"Delete Unsuccessful!",Toast.LENGTH_SHORT).show();
                        userInputChar.setText("");
                        userInputDec.setText("");

                    }//emd

                }catch(NumberFormatException nfe){
                    Toast.makeText(this, "Error, the number you entered was invalid!", Toast.LENGTH_SHORT).show();

                }//end try/catch

            }//end else

        }//end else if deleteRadBtn is checked

    }//end executeQuery

}
