package com.syntactec.subreddify;

import android.app.Application;

/**
 * This class is the main application class for the app.
 */
public class SubreddifyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .subreddifyApplicationModule(new SubreddifyApplicationModule(this))
                .build();

        applicationComponent.inject(this);
    }

}
