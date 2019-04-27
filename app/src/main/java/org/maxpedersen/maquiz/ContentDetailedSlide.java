package org.maxpedersen.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ContentDetailedSlide extends AppCompatActivity {
    //Declaring attributes that is needed for this class
    private FloatingActionButton fab;
    private static int i;
    private String contentTitle;
    private String title;
    //On creation we determine the Adapter classes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detailed_slide);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);
        //We also declare the tabs at the top of the content slider adapter
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        fab = findViewById(R.id.fab);
        Intent intent = getIntent();
        i = intent.getIntExtra("arrayIdx", 0);
        final Content selectedContent = DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase()
                .contentDAO().getContents().get(i);


        // Because the content slider view is determined dynamically, the pager needs to know when the data has changed in order to change the layout to show the data
        sectionsPagerAdapter.notifyDataSetChanged();
        //Sets an onclick listener for the YouTube button */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentDetailedSlide.this,YoutubeActivity.class);
                intent.putExtra("arrayIdx", i);
                intent.putExtra("youtubeValue", selectedContent.getContent_page1().split("URI:")[1]);




                startActivity(intent);
            }
        });
        //Retrieves the title of the content for using the getTitles method for the youtubeFAB method
        contentTitle = getTitles();
        youtubeFAB(fab);
    }
    //Global variable to allow the ui.main classes to access which index was clicked so they can access the properties of the object
    static public int getI(){
        return i;
    }
    //Determines the scenario as to whether or not to show the YouTube FAB
    public void youtubeFAB(FloatingActionButton fab){
        if(contentTitle.equals("Knowledge sharing articles & videos")) {
            fab.show();
        }
        else {
            fab.hide();
        }
    }

    public String getTitles(){
        List<Content> list = DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase()
                .contentDAO().getContents();
        String title = list.get(i).getTopic();
        return title;
    }
}