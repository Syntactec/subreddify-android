package com.syntactec.subreddify.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * This class models the Reddit REST API.
 */
public interface RedditService {
    @GET("r/{subreddit}/new.json?count=3")
    Call<List<RedditPost>> getPostsNewerThan(@Path("subreddit") String subreddit, @Query("before") String postName);

    @GET("r/{subreddit}/comments.json?count=3")
    Call<List<RedditComment>> getCommentsNewerThan(@Path("subreddit") String subreddit, @Query("before") String postName);
}
