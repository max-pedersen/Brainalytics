package com.example.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container, false);
        //postingUserDetail();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserDetails();
    }

    public void getUserDetails(){
        ArrayList<Home> list = Home.testDetails();
        Object test = list.get(0);
        String name = ((Home) test).getName();
        String zID = ((Home) test).getzID();
        int i = ((Home) test).getXP();
        String xp = String.valueOf(i);
        String show = "Welcome Back " + name;
        changingTextView(show, zID, xp);
    }

    public void changingTextView(String name, String zID, String xp){
        TextView nameTV = (TextView) getView().findViewById(R.id.welcomeUserTV);
        TextView xpTV = (TextView) getView().findViewById(R.id.xpNumber);
        nameTV.setText(name);
        xpTV.setText(xp);
    }

    public void postingUserDetail(){
        Bundle bundle = getArguments();
        if(bundle != null) {
            String name = bundle.getString("name");
            String zID = bundle.getString("zID");
            int XPi = bundle.getInt("XPdetail");
            Home.testDetails().add(new Home(name, zID, XPi));
        }else{
            Log.d("HomeFragment", "Failed passed into HF");
        }
    }



}
