package com.syntactec.subreddify.services;

import com.syntactec.subreddify.ApplicationComponent;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(dependencies = ApplicationComponent.class, modules = SyncModule.class)
public interface SyncComponent {
    void inject(SyncService syncService);
}
