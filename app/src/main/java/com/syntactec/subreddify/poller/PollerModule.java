package com.syntactec.subreddify.poller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Defines providers for creating objects involved with making Reddit REST API requests.
 */
@Module
class PollerModule {

    @Provides
    static RedditService provideRedditService() {
        Type redditPostListType = new TypeToken<List<RedditPost>>() {
        }.getType();
        Type redditCommentListType = new TypeToken<List<RedditComment>>() {
        }.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(redditPostListType, new RedditPostsDeserializer())
                .registerTypeAdapter(redditCommentListType, new RedditCommentsDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(RedditService.class);
    }
}
