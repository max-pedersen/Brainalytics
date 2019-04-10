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
        setContentView(R.layout.detailed_content);
        Intent intent = getIntent();
        int i = intent.getIntExtra("arrayIdx", 0);
        extract(i);
    }

    public void extract(int index){
        ArrayList<Content> content = Content.getTestContent();
        Object test = content.get(index);
        String contentParagraph = ((Content) test).getContent();
        String topicTitle = ((Content) test).getTopicTitle();
        //for YouTube API implementaion later
        String youtubeURL = ((Content) test).getYoutubeLink();
        TextView tvContentParagraph = findViewById(R.id.contentParagraph);
        TextView tvTopicTitle = findViewById(R.id.topicForDetailedContent);
        //YouTube API Button
        Button btnYoutube = findViewById(R.id.youtubeBtn);
        tvContentParagraph.setText(contentParagraph);
        tvTopicTitle.setText(topicTitle);
    }

    //When youtube API is implemented this is the method that will direct it there
    public void youtube(String link){

    }
    //If forum is implemented this is the method that will direct it there
    public void forum (int i){

    }

}
