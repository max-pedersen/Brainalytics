package org.maxpedersen.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.maxpedersen.maquiz.R;

import java.util.ArrayList;

public class ContentDetailed extends AppCompatActivity {
    TextView tvContentParagraph;
    TextView tvTopicTitle;
    Button btnYoutube;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Attaching the Class to the correct layout
        setContentView(R.layout.detailed_content);
        tvContentParagraph = findViewById(R.id.contentParagraph);
        tvTopicTitle = findViewById(R.id.topicForDetailedContent);
        btnYoutube = findViewById(R.id.youtubeBtn);


         /* set code such that if the week's content is of Knowledge Sharing, then the YouTube video link button will appear,
        else it will not */

        if(tvTopicTitle.toString() != ("Knowledge sharing articles & videos")) {
            btnYoutube.setVisibility(View.GONE);
        }
        else {
            btnYoutube.setVisibility(View.VISIBLE);
        }

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Youtube info page is navigated to
                Intent intent = new Intent(ContentDetailed.this,YoutubeActivity.class);
                startActivity(intent);
            }
        });


        //Getting the Extra data that was passed into the activity from the prior fragment, in this scenario it was the position of the Recycler View which is helpful in deciding which object in the array should inflate this activity
        Intent intent = getIntent();
        int i = intent.getIntExtra("arrayIdx", 0);
        //Passing in the data from the intent into the extract method which changes the data in the Activity to to match the topic that was clicked
        extract(i);
    }
    //Extract extracts the data from the ArrayList based on the index number that was passed in
    public void extract(int index){
        //Gets specific week of content
        Content weeksContentAtIndex = Content.getContent().get(index);
        //Insert this data into an array which stores it across page1 - 4
        String[] contentParagraph = {
                weeksContentAtIndex.getContent_page1(),
                weeksContentAtIndex.getContent_page2(),
                weeksContentAtIndex.getContent_page3(),
                weeksContentAtIndex.getContent_page4()
        };
        String topicTitle =  weeksContentAtIndex.getTopic();


        tvTopicTitle.setText(topicTitle);
    }


}
