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

import org.maxpedersen.maquiz.R;

import org.maxpedersen.maquiz.ui.main.SectionsPagerAdapter;

public class ContentDetailedSlide extends AppCompatActivity {

    FloatingActionButton fab;
    static int i;

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
        Log.d("ContentDetailedSlide", Integer.toString(i));
        sectionsPagerAdapter.notifyDataSetChanged();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContentDetailedSlide.this,YoutubeActivity.class);
                startActivity(intent);
            }
        });
    }

    static public int getI(){
        return i;
    }

    /*public void youtubeFAB(){
        if(tvTopicTitle.toString() != ("Knowledge sharing articles & videos")) {
            fab.setVisibility(View.GONE);
        }
        else {
            fab.setVisibility(View.VISIBLE);
        };
    }*/
}