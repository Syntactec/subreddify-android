package com.syntactec.subreddify.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.syntactec.subreddify.resources.RedditPost;
import com.syntactec.subreddify.resources.RedditResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.List;

/**
 * This class defines a service which will schedule the notification service to run at the user defined interval.
 */
public class PollerService extends IntentService {
    @Inject
    RedditResource redditResource;

    public PollerService() {
        super("PollerService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PollerComponent pollerComponent = DaggerPollerComponent.builder()
                .pollerModule(new PollerModule(this))
                .build();

        pollerComponent.inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String subredditQueryString = sharedPreferences.getString("choose_subreddits", "")
                .replaceAll(",", "+");

        if (subredditQueryString.isEmpty()) {
            return;
        }

        String lastPostName = sharedPreferences.getString("last_post_name", "");

        Call<List<RedditPost>> request = redditResource.getPostsNewerThan(subredditQueryString, lastPostName);
        request.enqueue(new Callback<List<RedditPost>>() {
            @Override
            public void onResponse(Call<List<RedditPost>> call, Response<List<RedditPost>> response) {
                if (response.isSuccessful()) {
                    List<RedditPost> posts = response.body();

                    if (posts.isEmpty()) {
                        return;
                    }

                    RedditPost firstPost = posts.get(0);
                    sharedPreferences.edit().putString("last_post_name", firstPost.getName()).apply();

                    for (RedditPost post : posts) {
                        Log.d("REST", post.getTitle());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<RedditPost>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
