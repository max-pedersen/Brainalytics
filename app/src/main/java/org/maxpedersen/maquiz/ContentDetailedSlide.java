package org.maxpedersen.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.maxpedersen.maquiz.R;

import org.maxpedersen.maquiz.ui.main.PlaceholderFragment;
import org.maxpedersen.maquiz.ui.main.SectionsPagerAdapter;

import java.util.List;

public class ContentDetailedSlide extends AppCompatActivity {

    private FloatingActionButton fab;
    private static int i;
    private String contentTitle;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detailed_slide);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        fab = findViewById(R.id.fab);

        Intent intent = getIntent();
        i = intent.getIntExtra("arrayIdx", 0);
        sectionsPagerAdapter.notifyDataSetChanged();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentDetailedSlide.this,YoutubeActivity.class);
                startActivity(intent);
            }
        });

        contentTitle = getTitles();
        youtubeFAB(fab);
    }


    static public int getI(){
        return i;
    }

    public void youtubeFAB(FloatingActionButton fab){
        if(contentTitle.equals("Knowledge sharing articles & videos")) {
            fab.show();
        }
        else {
            fab.hide();
        };
    }

    public String getTitles(){
        List<Content> list = Content.getContent();
        String title = list.get(i).getTopic();
        return title;
    }
}