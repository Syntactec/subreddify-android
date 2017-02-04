package com.syntactec.subreddify.services;

import android.app.Application;
import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.syntactec.subreddify.resources.RedditPost;
import com.syntactec.subreddify.resources.RedditPostsDeserializer;
import com.syntactec.subreddify.resources.RedditResource;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A module for Android-specific dependencies which require a {@link Context} or {@link Application} to create.
 */
@Module
public class PollerModule {
    private final PollerService pollerService;

    public PollerModule(PollerService pollerService) {
        this.pollerService = pollerService;
    }

    @Provides
    public PollerService pollerService() {
        return pollerService;
    }

    @Provides
    public RedditResource provideRedditResource() {
        Type redditPostListType = new TypeToken<List<RedditPost>>() {}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(redditPostListType, new RedditPostsDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(RedditResource.class);
    }
}
