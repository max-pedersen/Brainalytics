package org.maxpedersen.maquiz;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsArticle {

    public static final String NEWS_API_KEY = "c06da6e3036740838a0730ef5899cbb0";

    //https://newsapi.org/v2/everything?q=artificial+intelligence&language=en&from=2019-04-01&sources=abc-news-au,australian-financial-review, the-telegraph, the-new-york-times, &sortBy=relevancy&apiKey=c06da6e3036740838a0730ef5899cbb0



        @SerializedName("source")
        @Expose
        private Source source;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("urlToImage")
        @Expose
        private String urlToImage;
        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;
        @SerializedName("content")
        @Expose
        private String content;

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }
