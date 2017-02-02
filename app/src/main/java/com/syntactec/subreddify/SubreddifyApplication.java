package com.syntactec.subreddify;

import android.app.Application;
import com.syntactec.subreddify.resources.RedditResource;

import javax.inject.Inject;

/**
 * This class is the main application class for the app.
 */
public class SubreddifyApplication extends Application {
    @Inject
    RedditResource service;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .subreddifyApplicationModule(new SubreddifyApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    public RedditResource getRedditResource() {
        return service;
    }
}
