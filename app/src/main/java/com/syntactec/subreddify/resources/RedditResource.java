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
    @GET("r/{subredditQuery}/new.json?limit=3")
    Call<List<RedditPost>> getPostsNewerThan(@Path("subredditQuery") String subredditQuery, @Query("before") String postName);
}
