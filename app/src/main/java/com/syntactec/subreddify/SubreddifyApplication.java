package com.syntactec.subreddify;

import android.app.Application;
import com.syntactec.subreddify.services.RedditService;

import javax.inject.Inject;

/**
 * This class is the main application class for the app.
 */
public class SubreddifyApplication extends Application {
    @Inject
    RedditService service;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .subreddifyApplicationModule(new SubreddifyApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

    public RedditService getRedditService() {
        return service;
    }
}
