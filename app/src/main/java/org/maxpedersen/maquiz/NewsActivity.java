package org.maxpedersen.maquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

public class NewsActivity extends AppCompatActivity implements NewsArticleAdapter.OnNoteListener{

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
        /*Create handle for the RetrofitInstance interface*/
        NewsArticleService service = RetrofitClientInstance.getRetrofitInstance().create(NewsArticleService.class);
        Call<NewsArticleResponse> call = service.getRelevantArticles();
        call.enqueue(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {
                NewsArticleResponse object = response.body();
                int lengthOfArray = object.getTotalResults();
                mList = new ArrayList<Article>();
                //Uses the length of the JSON Array to use a for loop to extract the array and instantiate the Java object and add it to the array list
                for(int i=0; i < lengthOfArray; i++) {
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
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("NewsActivityThrowing", t.toString());
                Toast.makeText(NewsActivity.this, "Sorry, something has gone wrong. " +
                        "Please check your Internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Builds the RecyclerView to display the summaries news articles in cards
    public void buildRecyclerView(ArrayList<Article> list) {
        mRecyclerView = findViewById(R.id.newsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new NewsArticleAdapter(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    //When clicked this method will divert the user to the URL where the news article is stored
    public void extract(int index){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String link = mList.get(index).getUrl();
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }
    //Implements the onNoteClick and passes the RecyclerView index data to the extract method
    @Override
    public void onNoteClick(int position) {
        Log.d("main", "onNoteClicked: clicked" + position);
        extract(position);
    }
}



