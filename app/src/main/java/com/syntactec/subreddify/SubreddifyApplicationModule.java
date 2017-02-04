package com.syntactec.subreddify;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * A module for Android-specific dependencies which require a {@link Context} or {@link Application} to create.
 */
@Module
public class SubreddifyApplicationModule {
    private final Application application;

    public SubreddifyApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application application() {
        return application;
    }
}
