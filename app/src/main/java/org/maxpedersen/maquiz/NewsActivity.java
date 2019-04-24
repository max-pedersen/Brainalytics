package org.maxpedersen.maquiz;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.maxpedersen.maquiz.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    //private CustomAdapter adapter;
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
        Call<List<NewsArticle>> call = service.getRelevantArticles();
        call.enqueue(new Callback<List<NewsArticle>>() {
            @Override
            public void onResponse(Call<List<NewsArticle>> call, Response<List<NewsArticle>> response) {
                progressDialog.dismiss();

                response.body();
                Log.d(" from response: ", response.body() + (response.body() != null ? response.body().toString() : null));

            }

            @Override
            public void onFailure(Call<List<NewsArticle>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewsActivity.this, "Sorry, something has gone wrong. Please" +
                        "try again shortly", Toast.LENGTH_SHORT).show();
            }


        });
    }
}



