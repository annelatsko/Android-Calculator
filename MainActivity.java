package com.mycompany.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Math;


public class MainActivity extends ActionBarActivity {

    private StringBuilder numberString= new StringBuilder(0);
    private float savedNumber = 0;
    private int action = -1;
    //add=0
    //subtract=1
    //multiply=2
    //divide=3
    //squareRoot=4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNextScreen = (Button) findViewById(R.id.buttonNextScreen);
        buttonNextScreen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                //starts a new intent
                Intent nextScreen = new Intent(getApplicationContext(), SecondScreen.class);
                //sends information somewhere else
                TextView myTextView = (TextView) findViewById(R.id.textView);
                String total = (String) myTextView.getText();
                nextScreen.putExtra("total", total);
                Log.e("n", total);
                startActivity(nextScreen);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v){
        Button button = (Button) v;
        String bText = button.getText().toString();
        if(!numberString.equals(0)) {
            numberString.append(bText);
        }
        else{
            numberString = new StringBuilder(bText);
        }

        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText(numberString);
    }
    public void onAddButtonClick(View v){


        if(action != -1) {
            logic();
        }
        else {
            //saves the original number to savedNumber
            savedNumber = Float.parseFloat(numberString.toString());
            numberString = new StringBuilder(0);
        }
        action = 0;
    }
    public void onSubtractButtonClick(View v){

        if(action != -1) {
            logic();
        }
        else {
            //saves the original number to savedNumber
            savedNumber = Float.parseFloat(numberString.toString());
            numberString = new StringBuilder(0);
        }
        action = 1;
    }
    public void onMultiplyButtonClick(View v){

        if(action != -1) {
            logic();
        }
        else {
            //saves the original number to savedNumber
            savedNumber = Float.parseFloat(numberString.toString());
            numberString = new StringBuilder(0);
        }
        action = 2;
    }
    public void onDivideButtonClick(View v){
        if(action != -1) {
            logic();
        }
        else {
            //saves the original number to savedNumber
            savedNumber = Float.parseFloat(numberString.toString());
            numberString = new StringBuilder(0);
        }
        action = 3;
    }
    public void onEqualsButtonClick(View v){
        logic();
        numberString = new StringBuilder(Float.toString(savedNumber));
        savedNumber = 0;
        action = -1; //resets the action variable
    }

    public void logic(){
        //gets the current number
        float number = Integer.parseInt(numberString.toString());
        float total = 0;
        TextView myTextView = (TextView) findViewById(R.id.textView);
        String stringTotal;
        if(action == 0){
            total = number + savedNumber;
        }
        //subtraction
        else if(action == 1){
            total = savedNumber - number;
        }
        //multiplication
        else if(action == 2){
            total = number * savedNumber;
        }
        //division
        else if(action == 3){
            total = savedNumber / (float)number;
        }
        else{
            total = number;
        }
        savedNumber = total;
        stringTotal = String.valueOf(total); //makes a string of the total to set the text
        numberString = new StringBuilder(0); //stores the total of the new numberString
        myTextView.setText(stringTotal);
    }


    public void onClearButtonClick(View v){
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText("0");
        numberString = new StringBuilder(0); //resets the current number to zero
        action = -1; //resets the action variable
    }

    public void onDeleteButtonClick(View v){
        TextView myTextView = (TextView) findViewById(R.id.textView);
        if(numberString.length() >= 1) {
            numberString.setLength(numberString.length() - 1);
            myTextView.setText(numberString);
        }
        else{
            numberString = new StringBuilder(0);
            myTextView.setText(numberString);
        }
    }
}
