package org.maxpedersen.maquiz;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.maxpedersen.maquiz.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(NewsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        NewsArticleService service = RetrofitClientInstance.getRetrofitInstance().create(NewsArticleService.class);
        Call<NewsArticleResponse> call = service.getRelevantArticles();
        String diagnostics = call.toString();
        Log.d("NewsActivity", diagnostics);
        call.enqueue(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {
                progressDialog.dismiss();
                NewsArticleResponse object = response.body();
                String data = object.getArticles().get(0).getAuthor();
            }

            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("NewsActivityThrowing", t.toString());
                Toast.makeText(NewsActivity.this, "Sorry, something has gone wrong. Please" +
                        "try again shortly", Toast.LENGTH_SHORT).show();
            }


        });
    }
}



