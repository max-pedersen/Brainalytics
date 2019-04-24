package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.maquiz.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.ArrayList;
import java.util.List;

public class UserDetail extends AppCompatActivity
        //implements IPickResult
        {
    private TextInputLayout textInputName;
    private TextInputLayout textInputzID;
    private ImageView imageView;
    private String input;

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputName = findViewById(R.id.text_input_name);
        textInputzID = findViewById(R.id.text_input_zID);
        //imageView=findViewById(R.id.te)












/*        PickImageDialog.build(new PickSetup()).show(this);
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            getImageView().setImageBitmap(r.getBitmap());

            //Image path
            //r.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }}*/

    }



    private Boolean validateName(){
         String firstNameInput = textInputName.getEditText().getText().toString().trim();


        if(firstNameInput.isEmpty()){
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
                String zIDInput = textInputzID.getEditText().getText().toString().trim();
                String firstNameInput = textInputName.getEditText().getText().toString().trim();
                int zID = Integer.parseInt(textInputzID.getEditText().getText().toString().trim());



                if(!validatezID() | !validateName()){
            return;
            //TODO add String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        }

        int evaluator = DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase().userDAO().checkExists(zID);

        if (evaluator > 0) {
            // User thus exists in the database
            input = "Welcome back " + firstNameInput;
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            // show toast
            // then store the zID so that you can query across other things
            UserValueCapture.setUserMsg(input);
        }

        else if (evaluator == 0) {
            User signedInUser = new User(zID, firstNameInput);
            DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase()
                    .userDAO().insertUser(signedInUser);
            //
            input = "Welcome " + firstNameInput;
            Log.d(" from welcome", " " + DatabaseService.getDbInstance(getApplicationContext())
                    .getAppDatabase().userDAO().getUserName(zID));
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            UserValueCapture.setUserMsg(input);
        }


        int XP = 1200;
        UserValueCapture.setzIDGlobal(zID);
        UserValueCapture.setNameGlobal(firstNameInput);


//        List<User> batches = new ArrayList<User>();
//        batches.add(new User(5178336, "Max"));
//        batches.add(new User(5134298, "Alex"));
//        batches.add(new User(5267737, "Justin"));
//        batches.add(new User(5162322, "Jeff"));
//        batches.add(new User(5263324, "Tina"));
//        batches.add(new User(5133231, "Jiahao"));
//        batches.add(new User(4911132, "Andrew"));
//        batches.add(new User(5003211, "Nick"));
//        batches.add(new User(5243663, "Kevin"));
//
//        db.userDAO().insertBatchUsers(batches);
//
//        List<Result> resultbatch = new ArrayList<Result>();
//        resultbatch.add(new Result(200, 10,5178336 ));
//        resultbatch.add(new Result(202, 40,5134298 ));
//        resultbatch.add(new Result(204, 100,5267737 ));
//        resultbatch.add(new Result(206, 78,5162322 ));
//        resultbatch.add(new Result(208, 20,5263324 ));
//        resultbatch.add(new Result(210, 20,5133231 ));
//        resultbatch.add(new Result(212, 75,4911132 ));
//        resultbatch.add(new Result(214, 30,5003211 ));
//        resultbatch.add(new Result(216, 5,5243663 ));
//        db.resultDAO().insertBatchResults(resultbatch);

        // need to create an instance of User and pass it over
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }


}
