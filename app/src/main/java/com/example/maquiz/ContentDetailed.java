package com.example.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ContentDetailed extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Attaching the Class to the correct layout
        setContentView(R.layout.detailed_content);
        //Getting the Extra data that was passed into the activity from the prior fragment, in this scenario it was the position of the Recycler View which is helpful in deciding which object in the array should inflate this activity
        Intent intent = getIntent();
        int i = intent.getIntExtra("arrayIdx", 0);
        //Passing in the data from the intent into the extract method which changes the data in the Activity to to match the topic that was clicked
        extract(i);
    }
//Extract extracts the data from the ArrayList based on the index number that was passed in
    public void extract(int index){
        ArrayList<Content> content = Content.getTestContent();
        //Gets the specific object out of the ArrayList
        Object test = content.get(index);
        //Establishes String varaible for certain data within the object
        String contentParagraph = ((Content) test).getContent();
        String topicTitle = ((Content) test).getTopicTitle();
        //for YouTube API implementaion later
        String youtubeURL = ((Content) test).getYoutubeLink();
        //Sets the strings extracts from the object to the Activity
        TextView tvContentParagraph = findViewById(R.id.contentParagraph);
        TextView tvTopicTitle = findViewById(R.id.topicForDetailedContent);
        //YouTube API Button
        Button btnYoutube = findViewById(R.id.youtubeBtn);
        tvContentParagraph.setText(contentParagraph);
        //Sets the strings extracts from the object to the Activity
        tvTopicTitle.setText(topicTitle);
    }

    //When youtube API is implemented this is the method that will direct it there
    public void youtube(String link){

    }
    //If forum is implemented this is the method that will direct it there
    public void forum (int i){

    }

}
