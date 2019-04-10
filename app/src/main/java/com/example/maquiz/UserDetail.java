package com.example.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDetail extends AppCompatActivity {
    private TextInputLayout textInputName;
    private TextInputLayout textInputzID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputName = findViewById(R.id.text_input_name);
        textInputzID = findViewById(R.id.text_input_zID);

    }
    private Boolean validateName(){
        String enameInput = textInputName.getEditText().getText().toString().trim();

        if(enameInput.isEmpty()){
            textInputName.setError("Field cannot be empty");
            return false;
        } else {
            textInputName.setError(null);
            textInputName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatezID(){
        String zIDInput = textInputzID.getEditText().getText().toString().trim();

        if(zIDInput.isEmpty()){
            textInputzID.setError("Field cannot be empty");
            return false;
        }
        if (zIDInput.length() > 7){
            textInputzID.setError("zID is too long");
            return false;
        }
        if (zIDInput.length() < 7){
            textInputzID.setError("zID is too short");
            return false;
        }
        else {
            textInputzID.setError(null);
            textInputzID.setErrorEnabled(false);
            return true;
        }
    }

    public void confirmInput(View v){
        if(!validatezID() | !validateName()){
            return;
        }
        String input = "successful";
        String name = textInputName.getEditText().getText().toString();
        String zID = textInputzID.getEditText().getText().toString();
        int XP = 0;
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        ArrayList<Home> list = Home.testDetails();
        Home object  = list.get(0);
        object.setName(name);
        object.setzID(zID);
        object.setXP(XP);
        Log.d("UserDetail", object.getName() + " " +object.getzID()+ " " + object.getXP());
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
