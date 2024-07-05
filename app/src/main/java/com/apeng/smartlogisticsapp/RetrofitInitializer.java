package com.apeng.smartlogisticsapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.apeng.smartlogisticsapp.service.LoginService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer implements Initializer<Retrofit> {

    public static Retrofit RETROFIT;

    /**
     * Initializes and a component given the application {@link Context}
     *
     * @param context The application context.
     */
    @NonNull
    @Override
    public Retrofit create(@NonNull Context context) {
        initRETROFIT();
        return RETROFIT;
    }

    /**
     * @return A list of dependencies that this {@link Initializer} depends on. This is
     * used to determine initialization order of {@link Initializer}s.
     * <br/>
     * For e.g. if a {@link Initializer} `B` defines another
     * {@link Initializer} `A` as its dependency, then `A` gets initialized before `B`.
     */
    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }

    private static void initRETROFIT() {
        RETROFIT = new Retrofit.Builder()
                .baseUrl(LoginService.SERVER_URL)
                .client(new OkHttpClient().newBuilder().cookieJar(new SessionCookieJar()).build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    private static class SessionCookieJar implements CookieJar {

        private List<Cookie> cookies;

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (url.encodedPath().endsWith("login")) {
                this.cookies = new ArrayList<>(cookies);
            }
        }


        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            if (!url.encodedPath().endsWith("login") && cookies != null) {
                return cookies;
            }
            return Collections.emptyList();
        }
    }
}
