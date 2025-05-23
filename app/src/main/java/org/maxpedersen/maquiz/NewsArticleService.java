package org.maxpedersen.maquiz;

import retrofit2.http.GET;
import retrofit2.Call;


public interface NewsArticleService {
    //stores the API call
    @GET("everything?q=artificial+intelligence&language=en&from=2019-04-01&sources=abc-news-au,australian-financial-review&sortBy=publishedAt&apiKey=c06da6e3036740838a0730ef5899cbb0")
    Call<NewsArticleResponse> getRelevantArticles();

    @GET("everything?q=data+visualisation&language=en&from=2019-04-01&sources=abc-news-au,australian-financial-review&sortBy=publishedAt&apiKey=c06da6e3036740838a0730ef5899cbb0")
    Call<NewsArticleResponse> getRelevantDataVizArticles();
}