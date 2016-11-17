package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.id;

public class NumberBaseConvertor extends AppCompatActivity{

    //create Objects to hold items from Screen
    //Textviews and Edittexts
    TextView txtOutput;
    EditText txtInput;

    //radioButtons
    RadioButton rbDec;
    RadioButton rbBin;
    RadioButton rbOct;
    RadioButton rbHex;


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

        //assign onScreenObjects
        txtOutput = (TextView)findViewById(R.id.txtBaseConvertorOutput);
        txtInput = (EditText)findViewById(R.id.txtUserInputBaseConvertor);

        rbDec = (RadioButton) findViewById(R.id.radBtnDec);
        rbBin = (RadioButton) findViewById(R.id.radBtnBin);
        rbOct = (RadioButton) findViewById(R.id.radBtnOct);
        rbHex = (RadioButton) findViewById(R.id.radBtnHex);

        //this line may not be needed but there's an xml problem that it isn't setting rbDec to be checked so here it is anyway
        rbDec.setChecked(true);

    }//end onCreate


    //if convert button is clicked
    public void convertInput(View v){
        //declare variables
        String userInput;
        userInput = txtInput.getText().toString();

        //clear output section
        txtOutput.setText("");

        //if the decimal radio button is checked
        if(rbDec.isChecked()){
            //try/ catch to check for errors
            try{
                getBinaryVal(userInput);
                getOctalVal(userInput);
                getHexadecimalVal(userInput);

            }catch(NumberFormatException nfe){
                Toast.makeText(this, "Error you did not enter a valid decimal input!", Toast.LENGTH_SHORT).show();

            }//end try/catch

        }//end if rbDec is checked

        else if(rbBin.isChecked()){
            //try/catch to check for errors
            try{
                String temp = getDecimalVal(userInput);
                txtOutput.append("\nThe Decimal equivalent of " + userInput + " is: \t" + temp);
                getOctalVal(temp);
                getHexadecimalVal(temp);

            }catch(NumberFormatException nfe){
                Toast.makeText(this,"Error, you didn't enter a valid binary number!", Toast.LENGTH_SHORT).show();

            }//end try/catch

        }//end else if

    }//end convertInput

    public String getDecimalVal(String value){

        //temporary value for getting decimalValue
        int temp;

        //check to see if binary radio button is checked
        if(rbBin.isChecked()){
            temp = Integer.parseInt(value,2);

        }//end if rbBin.isChecked()
        //oct button is checked
        else if(rbOct.isChecked()){
            temp = Integer.parseInt(value,8);

        }//end else if oct is checked
        //hexadecimal button is checked
        else if(rbHex.isChecked()){
            temp= Integer.parseInt(value.trim(),16);

        }//end if hex is changed



    }//end getDecimalVal

    public void getBinaryVal(String value){
        int temp = Integer.parseInt(value);
        String binaryVal = Integer.toString(temp,2);
        txtOutput.append("\nThe Binary of equivalent of " + value + " is: \t" + binaryVal);

    }//end getBinaryVal

    public void getOctalVal(String value){
        int temp = Integer.parseInt(value);
        String octalVal = Integer.toString(temp, 8);
        txtOutput.append("\nThe Octal equivalent of " + value + " is: \t" + octalVal);

    } //end getOctalVal
    public void getHexadecimalVal(String value){
        int temp = Integer.parseInt(value);
        String hexVal = Integer.toString(temp, 16);
        txtOutput.append("\nThe Hexidecimal equivalent of " + value + " is: \t" + hexVal);

    }//end getHexadecimalVal


}//end class
