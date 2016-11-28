package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BinaryTextConvertor extends AppCompatActivity {

    //Create Textview for all functions
    TextView txtOutput;

    //variables to ensure that datatypes are not mixed
    int currentState=0;
    int previousState=0;
    int stateCookie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_text_convertor);
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
                intent = new Intent(BinaryTextConvertor.this, MainActivity.class);
                startActivity(intent);
                finish();

            }//end on click

        });//end onclicklistener

        //assign txtOutput
        txtOutput = (TextView)findViewById(R.id.txtOutputBinTxtConv);

    }//end onCreate

    //clear the output
    public void clearOutput(View v){
        txtOutput.setText("");

    }//end clearOutput

    //add a space to the output
    public void addSpace(View v){
        txtOutput.append(" ");

    }//end addSpace

    public void convertUserInput(View v){
        //Declare neccessary variables
        RadioButton radBtnCharacter = (RadioButton)findViewById(R.id.radBtnCharacterBinTxtConv);
        RadioButton radBtnBinary = (RadioButton)findViewById(R.id.radBtnBinaryBinTxtConv);
        EditText txtUserInput = (EditText)findViewById(R.id.txtUserInputTxtBinCon);

        //if the binary radiobutton is checked
        if(radBtnBinary.isChecked()){
            currentState = 0;

        }//end if

        //else case for the character radiobutton being checked
        else{
            currentState = 1;

        }//end else

        //function to ensure user doesn't have characters and binary mixed together
        if(currentState != previousState && stateCookie==1){
            Toast.makeText(this, "Note, you cannot mix datatypes. Your output will be cleared.",Toast.LENGTH_SHORT).show();
            txtOutput.setText("");

        }//end if

        //if there's no difference between the states
        else{
            //if user is entering a radiobutton character
            if(radBtnCharacter.isChecked()){
                //try for number format exceptions
                try{
                    //verify input length
                    if(txtUserInput.getText().toString().length()!=1){
                        Toast.makeText(this,"Error, character input must consist of only one character!", Toast.LENGTH_SHORT).show();

                    }//end if
                    //if length is fine, convert it to binary
                    else{
                        char userValue = txtUserInput.getText().toString().charAt(0);
                        int temp = (int)userValue;
                        byte tempByte = (byte) temp;
                        String appendValue = String.format("%8s", Integer.toBinaryString(tempByte & 0xFF));
                        txtOutput.append(appendValue + " ");

                    }//end else
                }catch(NumberFormatException nfe){
                    Toast.makeText(this, "Error, you entered an invalid character!",Toast.LENGTH_SHORT);

                }//end catch

            }//end if character is the input type
            else{
                //try to verify user input
                try{
                    //make sure length is valid
                    if (txtUserInput.getText().toString().length()<1 || txtUserInput.getText().toString().length()>8){
                        Toast.makeText(this, "Error, your input must be between 1 and 8 characters in length!",Toast.LENGTH_SHORT).show();

                    }//end if
                    //if length is valid
                    else{
                        //parse user input to a binary integer
                        int userInput = Integer.parseInt(txtUserInput.getText().toString(),2);

                        //make sure that user is not requesting a system command
                        if((userInput>=0 && userInput<=31)||userInput==127){
                            Toast.makeText(this,"Error, you have entered a system command. These may not be entered!",Toast.LENGTH_SHORT).show();

                        }//end if
                        else{
                            char returnVal = (char)userInput;
                            txtOutput.append("" + returnVal);

                        }//end else

                    }//end else

                }catch(NumberFormatException nfe){
                    Toast.makeText(this, "Error you entered an invalid binary number!",Toast.LENGTH_SHORT).show();

                }//end catch

            }//end else if the character is a binary number

        }//end else

        //clear input
        txtUserInput.setText("");

        //used to indicate what type the user just entered
        if(radBtnBinary.isChecked()){
            previousState = 0;

        }//end if
        else{
            previousState = 1;

        }//end else

        //used to indicate that the user has passed through this section of the program at least once before
        stateCookie = 1;

    }//end convertUserInput

}//end class
