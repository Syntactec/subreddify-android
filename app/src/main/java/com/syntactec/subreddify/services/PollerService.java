package com.syntactec.subreddify.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
public class PollerService extends Service {
    @Inject
    RedditResource redditResource;

    @Override
    public void onCreate() {
        super.onCreate();

        PollerComponent pollerComponent = DaggerPollerComponent.builder()
                .pollerModule(new PollerModule(this))
                .build();

        pollerComponent.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String subredditQueryString = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("choose_subreddits", "")
                .replaceAll(",", "+");

        Call<List<RedditPost>> request = redditResource.getPostsNewerThan(subredditQueryString, "");
        request.enqueue(new Callback<List<RedditPost>>() {
            @Override
            public void onResponse(Call<List<RedditPost>> call, Response<List<RedditPost>> response) {
                if (response.isSuccessful()) {
                    for (RedditPost post : response.body()) {
                        Log.d("REST", post.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RedditPost>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
