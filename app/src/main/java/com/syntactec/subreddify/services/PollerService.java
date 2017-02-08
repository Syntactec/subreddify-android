package com.syntactec.subreddify.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.syntactec.subreddify.R;
import com.syntactec.subreddify.resources.RedditPost;
import com.syntactec.subreddify.resources.RedditResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Call<List<RedditPost>> request = redditResource.getNewestPosts(subredditQueryString);
        request.enqueue(new Callback<List<RedditPost>>() {
            @Override
            public void onResponse(Call<List<RedditPost>> call, Response<List<RedditPost>> response) {
                if (response.isSuccessful()) {
                    List<RedditPost> posts = response.body();

                    RedditPost firstPost = posts.get(0);

                    if (firstPost.getName().equals(sharedPreferences.getString("last_post_name", ""))) {
                        return;
                    }

                    sharedPreferences.edit().putString("last_post_name", firstPost.getName()).apply();

                    Set<String> subreddits = new HashSet<>();
                    for (RedditPost post : posts) {
                        subreddits.add(post.getSubreddit());
                    }

                    String subredditsDetail = TextUtils.join(",", subreddits);

                    NotificationCompat.Builder notificationBuilder =
                            new NotificationCompat.Builder(PollerService.this)
                                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                    .setContentTitle("New subreddit content!")
                                    .setContentText("There is new content at the following subreddits:")
                                    .setStyle(new NotificationCompat.BigTextStyle()
                                            .bigText(subredditsDetail));

                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    notificationManager.notify(0, notificationBuilder.build());
                }
            }

            @Override
            public void onFailure(Call<List<RedditPost>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
