package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maquiz.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserDetails();

    }

    public void getUserDetails(){

        //TODO get info from the UserDetail fragment that has been passed

        /*Bundle bundle = getInt
        if(bundle != null) {
            String name = bundle.getString("name");
            int zID = bundle.getInt("zID");
            int XPi = bundle.getInt("XPdetail");
            Home.testDetails().add(new Home(name, zID, XPi));
        }else{
            Log.d("HomeFragment", "Failed passed into HF");
        }*/


        String name = "Max";
        int zID = 5164270;
        int i = 1200;
        String xp = String.valueOf(i);
        String show = "Welcome Back " + name + " !";
        changingTextView(show, zID, xp);
    }

    public void changingTextView(String name, int zID, String xp){
        TextView nameTV = (TextView) getView().findViewById(R.id.welcomeUserTV);
        TextView xpTV = (TextView) getView().findViewById(R.id.xpNumber);
        nameTV.setText(name);
        xpTV.setText(xp);
    }




}
