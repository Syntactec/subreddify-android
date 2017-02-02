package com.syntactec.subreddify;

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

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.util.List;

/**
 * A module for Android-specific dependencies which require a {@link Context} or {@link Application} to create.
 */
@Module
public class SubreddifyApplicationModule {
    private final Application application;

    public SubreddifyApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application application() {
        return application;
    }

    @Provides
    @Singleton
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
