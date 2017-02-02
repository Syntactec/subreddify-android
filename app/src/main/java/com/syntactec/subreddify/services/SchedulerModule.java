package com.syntactec.subreddify.services;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * A module for Android-specific dependencies which require a {@link Context} or {@link Application} to create.
 */
@Module
public class SchedulerModule {
    private final SchedulerService schedulerService;

    public SchedulerModule(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Provides
    @Singleton
    public SchedulerService schedulerService() {
        return schedulerService;
    }
}
