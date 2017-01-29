package com.syntactec.subreddify.poller;

import dagger.Component;

/**
 * Defines the Poller component.
 */
@Component(modules = PollerModule.class)
public interface Poller {
    /**
     * Gets the Reddit service to use to make calls into the Reddit API.
     *
     * @return the {@link RedditService} to use to get data
     */
    RedditService getRedditService();
}
