package org.maxpedersen.maquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.maxpedersen.maquiz.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class YoutubeActivity extends YouTubeBaseActivity  {
    private static final String TAG = "YoutubeActivity";
    static final String API_KEY = "AIzaSyBka9Qp1HYeNOCMHcBV9Em3C_CIaMAh7rk";
    public String YT_VIDEO_ID = "5Zg-C8AAIGg";
    private int i;
    //Set up variables for youtube player API
    private YouTubePlayerView myYoutubePlayerView;
    private YouTubePlayer.OnInitializedListener initializedListener;
    private Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        goBackBtn = findViewById(R.id.buttonYoutubeView);

        initializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                youTubePlayer.setPlaybackEventListener(playbackEventListener);
                youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
                if (!wasRestored) {
                    youTubePlayer.cueVideo(YT_VIDEO_ID);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                final int REQUEST_CODE = 1;
                if (youTubeInitializationResult.isUserRecoverableError()) {
                } else {
                    String errorMessage = String.format("There was an error initializing the Youtube Player (%1$s)", youTubeInitializationResult.toString());
                }
            }
        };
        myYoutubePlayerView = findViewById(R.id.youtube_view);
        myYoutubePlayerView.initialize(API_KEY, initializedListener);
        inflateTitle();
        goBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "Video has paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
        }

        @Override
        public void onBuffering(boolean b) {
        }

        @Override
        public void onSeekTo(int i) {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {
        }

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {
        }

        @Override
        public void onVideoEnded() {
            //Add XP when a user finishes a video
            Toast.makeText(YoutubeActivity.this, "Great work " +
                    UserValueCapture.nameGlobal + " , you've earned 3 XP!", Toast.LENGTH_LONG).show();
            Result tempObj = new Result(3, UserValueCapture.zIDGlobal, "video content");
            DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase().resultDAO().insertResult(tempObj);
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
        }
    };
    //Inflates the title for the youtube activity class
    private void inflateTitle(){
        Intent intent = getIntent();
        i = intent.getIntExtra("arrayIdx", 0);
        List<Content> list = (ArrayList<Content>) DatabaseService.getDbInstance(getApplicationContext()).getAppDatabase()
                .contentDAO().getContents();
        String youtubeTitle = list.get(i).getTopic();
        TextView youtubeTV = findViewById(R.id.youtubeTitleTV);
        youtubeTV.setText(youtubeTitle);
    }
}

