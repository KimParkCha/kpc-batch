package com.ssafy.kpc.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

    private static final String BASE_URL = "https://new.land.naver.com/";

    @Bean
    public OkHttpClient okHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {
            System.out.println(String.format("OKHttp Logger : %s", message));
        });
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String url = request.url().toString();
                url = url.replace("%25", "%");
                Request newRequest = new Request.Builder().url(url).build();
                return chain.proceed(newRequest);
            }
        };
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(ScalarsConverterFactory.create()) // receive to string
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Bean
    public NaverAPIService serverAPIs(Retrofit retrofit) {
        return retrofit.create(NaverAPIService.class);
    }
}