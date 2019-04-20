package org.maxpedersen.maquiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.maquiz.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = extract();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        HomeFragment fragment = HomeFragment.newInstance(user.getZ_id(), user.getFirst_name());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                    }
                    switch(item.getItemId()){
                        case R.id.nav_content:
                            selectedFragment = new ContentFragment();
                            break;
                    }
                    switch(item.getItemId()){
                        case R.id.nav_quiz:
                            selectedFragment = new QuizFragment();
                            break;
                    }
                    switch(item.getItemId()){
                        case R.id.nav_leaderboard:
                            selectedFragment = new LeaderboardFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public void extract5(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        //intent.putExtra("arrayIdx", index);
        startActivity(intent);
    }

    public User extract(){
        Intent intent = getIntent();
        int zID = intent.getIntExtra("zID",0);
        String name = intent.getStringExtra("name");
        User user = new User(zID,name);
        return user;
    }
}
