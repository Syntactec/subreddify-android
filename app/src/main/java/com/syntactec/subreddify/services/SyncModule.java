package com.syntactec.subreddify.services;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * A module for Android-specific dependencies which require a {@link Context} or {@link Application} to create.
 */
@Module
public class SyncModule {
    private final SyncService syncService;

    public SyncModule(SyncService syncService) {
        this.syncService = syncService;
    }

    @Provides
    @Singleton
    public SyncService syncService() {
        return syncService;
    }

    @Provides
    public PendingIntent syncIntent() {
    }
}
