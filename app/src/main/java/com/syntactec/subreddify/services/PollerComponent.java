package com.syntactec.subreddify.services;

import dagger.Component;

@PerService
@Component(modules = PollerModule.class)
public interface PollerComponent {
    void inject(PollerService pollerService);
}
