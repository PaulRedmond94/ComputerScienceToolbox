package com.pauljredmond.computersciencetoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NumberBaseCalculator extends AppCompatActivity {

    //Declare objects to reference screen objects
    Spinner opSpinner;

    //TextView
    TextView outputView;

    //EditText
    EditText userInputVal1;
    EditText userInputVal2;

    //radioButtons
    RadioButton radBtnDec;
    RadioButton radBtnBin;
    RadioButton radBtnOct;
    RadioButton radBtnHex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_base_calculator);
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
                intent = new Intent(NumberBaseCalculator.this, MainActivity.class);
                startActivity(intent);
                finish();
            }//end on click

        });//end onclicklistener


        //set up spinner
        opSpinner= (Spinner) findViewById(R.id.spinOperations);
        //set up arrayAdapter
        //create arrayadapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operations,android.R.layout.simple_spinner_item);

        //specify layout to use for list of choices
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply adapter to spinner
        opSpinner.setAdapter(adapter);

        //assign screen objects to objects
        //TextView
        outputView= (TextView)findViewById(R.id.txtBaseCalcOutput);

        //EditText
        userInputVal1 = (EditText)findViewById(R.id.txtUserInput1);
        userInputVal2 = (EditText)findViewById(R.id.txtUserInput2);

        //radioButtons
        radBtnDec = (RadioButton)findViewById(R.id.radBtnDecBaseCalc);
        radBtnBin = (RadioButton)findViewById(R.id.radBtnBinaryBaseCalc);
        radBtnOct = (RadioButton)findViewById(R.id.radBtnOctBaseCalc);
        radBtnHex = (RadioButton)findViewById(R.id.radBtnHexBaseCalc);

        //line used just to focus on the first text field rather than the second one
        userInputVal1.requestFocus();

    }//end onCreate

    public void baseCalculate(View v){

        //variables for arithmetic
        int userInput1;
        int userInput2;
        int outputValue;

        //try to get current mode
        String mode="";
        try{
            //find out what the current item is in the spinner
            mode = opSpinner.getSelectedItem().toString();
        }catch(NullPointerException npe){
            Toast.makeText(this,"Error you didn't select an operation!",Toast.LENGTH_SHORT).show();

        }//end try/catch MAY NOT BE NEEDED!!!

        outputView.setText("");

        //check to see if anything is actually entered for input 1
        if(userInputVal1.getText().toString().length()==0){
            Toast.makeText(this, "Error, you didn't enter anything for input 1!",Toast.LENGTH_SHORT ).show();

        }//end if

        else if(userInputVal2.getText().toString().length()==0){
            Toast.makeText(this, "Error you didn't enter anything for input 2!",Toast.LENGTH_SHORT).show();

        }//end else if

        //if the decimal radio button is clicked
        else if(radBtnDec.isChecked()){

            //try/catch to very user input
            try{
                userInput1 = Integer.parseInt(userInputVal1.getText().toString());
                userInput2 = Integer.parseInt(userInputVal2.getText().toString());
                //switch for different modes
                switch(mode){
                    case "Addition":
                        outputValue= userInput1 + userInput2;

                        //set output view to display result
                        outputView.setText("\n" + userInputVal1.getText().toString() + " + " + userInputVal2.getText().toString() + " = " + outputValue);
                        break; //end addition

                    case "Subtraction":
                        outputValue = userInput1 - userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " - " + userInputVal2.getText().toString() + " = " + outputValue);
                        break; //end subtraction

                    case "Multiplication":
                        outputValue = userInput1 * userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " * " + userInputVal2.getText().toString() + " = " + outputValue);
                        break; //end multiplication

                    case "Division":
                        outputValue = userInput1 / userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " / " + userInputVal2.getText().toString() + " = " + outputValue + " R: " + userInput1%userInput2);
                        break;

                    default:
                        Toast.makeText(this, "Error, operation not found!",Toast.LENGTH_SHORT).show();

                }//end switch
            }catch(NumberFormatException nfe){
                Toast.makeText(getApplicationContext(), "Error you did not enter a valid decimal number!", Toast.LENGTH_SHORT).show();

            }//end try/catch

        }//end if the decimal radio button is ticked

        //if the binary radio button is checked
        else if(radBtnBin.isChecked()){
            try{
                //declare variables
                userInput1 = Integer.parseInt(userInputVal1.getText().toString(),2);
                userInput2 = Integer.parseInt(userInputVal2.getText().toString(),2);

                switch(mode){
                    case "Addition":
                        outputValue= userInput1 + userInput2;

                        //set output view to display result
                        outputView.setText("\n" + userInputVal1.getText().toString() + " + " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,2));
                        break; //end addition

                    case "Subtraction":
                        outputValue = userInput1 - userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " - " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,2));
                        break; //end subtraction

                    case "Multiplication":
                        outputValue = userInput1 * userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " * " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,2));
                        break; //end multiplication

                    case "Division":
                        outputValue = userInput1 / userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " / " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,2) + " R: " + Integer.toString(userInput1%userInput2,2));
                        break;//end binary?

                    default:
                        Toast.makeText(this, "Error, operation not found!",Toast.LENGTH_SHORT).show();

                }//end switch

            }catch(NumberFormatException nfe){
                Toast.makeText(this,"Error you entered an invalid Binary number!",Toast.LENGTH_SHORT).show();

            }//end try catch

        }//end if the binary radio button is checked

        //if the octal radio button is checked
        else if(radBtnOct.isChecked()){
            try{
                //declare variables
                userInput1 = Integer.parseInt(userInputVal1.getText().toString(),8);
                userInput2 = Integer.parseInt(userInputVal2.getText().toString(),8);

                switch(mode){
                    case "Addition":
                        outputValue= userInput1 + userInput2;

                        //set output view to display result
                        outputView.setText("\n" + userInputVal1.getText().toString() + " + " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,8));
                        break; //end addition

                    case "Subtraction":
                        outputValue = userInput1 - userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " - " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,8));
                        break; //end subtraction

                    case "Multiplication":
                        outputValue = userInput1 * userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " * " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,8));
                        break; //end multiplication

                    case "Division":
                        outputValue = userInput1 / userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " / " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,8) + " R: " + Integer.toString(userInput1%userInput2,8));
                        break;//end division

                    default:
                        Toast.makeText(this, "Error, operation not found!",Toast.LENGTH_SHORT).show();

                }//end switch

            }catch(NumberFormatException nfe){
                Toast.makeText(this,"Error you entered an invalid Octal number!",Toast.LENGTH_SHORT).show();

            }//end try catch

        }//end else if octal radio button is checked

        //if hexadecimal radio button is checked
        else if(radBtnHex.isChecked()){
            try{
                //declare variables
                userInput1 = Integer.parseInt(userInputVal1.getText().toString(),16);
                userInput2 = Integer.parseInt(userInputVal2.getText().toString(),16);

                switch(mode){
                    case "Addition":
                        outputValue= userInput1 + userInput2;

                        //set output view to display result
                        outputView.setText("\n" + userInputVal1.getText().toString() + " + " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,16));
                        break; //end addition

                    case "Subtraction":
                        outputValue = userInput1 - userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " - " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,16));
                        break; //end subtraction

                    case "Multiplication":
                        outputValue = userInput1 * userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " * " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,16));
                        break; //end multiplication

                    case "Division":
                        outputValue = userInput1 / userInput2;

                        //set output view to display results
                        outputView.setText("\n" + userInputVal1.getText().toString() + " / " + userInputVal2.getText().toString() + " = " + Integer.toString(outputValue,16) + " R: " + Integer.toString(userInput1%userInput2,16));
                        break;//end division

                    default:
                        Toast.makeText(this, "Error, operation not found!",Toast.LENGTH_SHORT).show();

                }//end switch

            }catch(NumberFormatException nfe){
                Toast.makeText(this,"Error you entered an invalid Octal number!",Toast.LENGTH_SHORT).show();

            }//end try catch

        }//end else if radbtnhex is checked

    }//end baseCalculate

}
