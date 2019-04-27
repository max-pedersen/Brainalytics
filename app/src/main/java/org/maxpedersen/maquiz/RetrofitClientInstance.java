package org.maxpedersen.maquiz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/* Based on Prakash Pun's Retrofit tutorial -
https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23
 */

public class RetrofitClientInstance {
        //Set up of retrofit instance
        private static Retrofit retrofit;
        private static final String BASE_URL = "https://newsapi.org/v2/";

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
