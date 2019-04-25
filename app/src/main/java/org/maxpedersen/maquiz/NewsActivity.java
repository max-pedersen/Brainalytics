package org.maxpedersen.maquiz;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

    private ProgressDialog progressDialog;
    private ArrayList<Article> mList;
    private RecyclerView mRecyclerView;
    private NewsArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        progressDialog = new ProgressDialog(NewsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        mRecyclerView = findViewById(R.id.newsRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
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
                String data = object.getArticles().get(2).getAuthor();
                Log.d("test", data);
                mList = new ArrayList<Article>();
                for(int i=0; i < 9; i++) {
                    Source tempSource = object.getArticles().get(i).getSource();
                    String tempAuthor = object.getArticles().get(i).getAuthor();
                    String tempTitle = object.getArticles().get(i).getTitle();
                    String tempDescription = object.getArticles().get(i).getDescription();
                    String tempUrl = object.getArticles().get(i).getUrl();
                    String tempUrlToImage = object.getArticles().get(i).getUrlToImage();
                    String temppublishedAt = object.getArticles().get(i).getPublishedAt();
                    String tempContent = object.getArticles().get(i).getContent();
                    Article tempObject = new Article(tempSource, tempAuthor, tempTitle, tempDescription, tempUrl, tempUrlToImage, temppublishedAt, tempContent);
                    mList.add(tempObject);
                }
                buildRecyclerView(mList);
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

    public void buildRecyclerView(ArrayList<Article> list) {
        mRecyclerView = findViewById(R.id.newsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewsArticleAdapter(list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}



