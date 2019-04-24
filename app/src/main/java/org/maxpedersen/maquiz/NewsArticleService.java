package org.maxpedersen.maquiz;

import retrofit2.http.GET;
import retrofit2.Call;
import org.maxpedersen.maquiz.NewsArticle;
import java.util.List;


public interface NewsArticleService {

    @GET("?q=artificial+intelligence&language=en&from=2019-04-01" +
            "&sources=abc-news-au,australian-financial-review, the-telegraph, the-new-york-times" +
            "&sortBy=relevancy" +
            "&apiKey=c06da6e3036740838a0730ef5899cbb0")
    Call<List<NewsArticle>> getRelevantArticles();
}