package com.syntactec.subreddify.resources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * This class models the Reddit resources the application uses.
 */
public interface RedditResource {
    @GET("r/{subreddit}/new.json?count=3")
    Call<List<RedditPost>> getPostsNewerThan(@Path("subreddit") String subreddit, @Query("before") String postName);
}
