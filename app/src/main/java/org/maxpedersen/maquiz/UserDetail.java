package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maquiz.R;

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
            //alex- why do you have a blank return here, wouldn't it make more sense to include the below code as part of if statement
            //TODO add String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        }
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Overall Database")
                .allowMainThreadQueries().build();

        String name = textInputName.getEditText().getText().toString();
        int zID = Integer.parseInt(textInputzID.getEditText().getText().toString());

        User signedInUser = new User(zID, name);
        //this inserts the user details into our database
        db.userDAO().insertUser(signedInUser);
        String test = signedInUser.getFirst_name();



        int XP = 1200;
        String input = "successful";
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        // need to create an instance of User and pass it over
        Log.d("UserDetail", signedInUser.getZ_id() + " " +signedInUser.getFirst_name()+ " " + XP);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("some_key", 3);
        startActivity(intent);
    }


}
