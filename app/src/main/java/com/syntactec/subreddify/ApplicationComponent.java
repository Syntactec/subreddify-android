package com.syntactec.subreddify;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = SubreddifyApplicationModule.class)
public interface ApplicationComponent {
    void inject(SubreddifyApplication application);
}
