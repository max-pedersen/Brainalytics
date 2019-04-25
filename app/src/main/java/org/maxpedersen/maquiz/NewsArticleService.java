package org.maxpedersen.maquiz;

import android.os.Build;
import android.support.annotation.RequiresApi;

import retrofit2.http.GET;
import retrofit2.Call;
import org.maxpedersen.maquiz.NewsArticle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public interface NewsArticleService {

    LocalDate time = java.time.LocalDate.now().minusDays(14);
    //TODO convert to string and pass as parameter into GET Request

    //@GET("everything/?q=artificial+intelligence&language=en&from=2019-04-01&sources=abc-news-au,australian-financial-review,the-telegraph,the-new-york-times&sortBy=relevancy&apiKey=c06da6e3036740838a0730ef5899cbb0")
    @GET("everything?q=artificial+intelligence&language=en&from=2019-04-01&sources=abc-news-au&sortBy=relevancy&apiKey=c06da6e3036740838a0730ef5899cbb0")
    Call<NewsArticleResponse> getRelevantArticles();
}