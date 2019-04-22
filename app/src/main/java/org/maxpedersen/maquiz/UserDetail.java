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

public class UserDetail extends AppCompatActivity
        //implements IPickResult
        {
    private TextInputLayout textInputName;
    private TextInputLayout textInputzID;
    private ImageView imageView;


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
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "Overall Database").allowMainThreadQueries().build();


        int evaluator = db.userDAO().checkExists(zID);

        if (evaluator > 0) {
            // User thus exists in the database
            String input = "Welcome back " + firstNameInput;
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            // show toast
            // then store the zID so that you can query across other things
        }

        else if (evaluator == 0) {
            User signedInUser = new User(zID, firstNameInput);
            db.userDAO().insertUser(signedInUser);
            //
            String input = "Welcome " + firstNameInput;
            Log.d(" from welcome", " " + db.userDAO().getUserName(zID));
            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }


        int XP = 1200;
        UserValueCapture.setzIDGlobal(zID);
        UserValueCapture.setNameGlobal(firstNameInput);

        // need to create an instance of User and pass it over
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
