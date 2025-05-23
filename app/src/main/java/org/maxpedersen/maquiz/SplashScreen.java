package org.maxpedersen.maquiz;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.maxpedersen.maquiz.R;

// This class sets up the Splash Screen when the user first runs the application
public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo);
        animateView(logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreen.this, UserDetail.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public void animateView(ImageView layout){
        ObjectAnimator fadeInText = ObjectAnimator.ofFloat(layout, View.ALPHA,0.0f, 1.0f).setDuration(500);
        ObjectAnimator fadeOutText = ObjectAnimator.ofFloat(layout, View.ALPHA,1.0f, 0.0f).setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(fadeInText, fadeOutText, fadeInText, fadeOutText);
        animatorSet.start();
    }

}
