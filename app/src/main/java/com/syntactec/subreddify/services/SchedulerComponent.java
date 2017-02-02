package com.syntactec.subreddify.services;

import com.syntactec.subreddify.ApplicationComponent;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(dependencies = ApplicationComponent.class, modules = SchedulerModule.class)
public interface SchedulerComponent {
    void inject(SchedulerService schedulerService);
}
