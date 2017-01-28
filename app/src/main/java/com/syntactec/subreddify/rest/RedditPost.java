package com.syntactec.subreddify.rest;

/**
 * This class models a Reddit post object.
 */
public class RedditPost {
    private String author;
    private boolean over18;
    private String title;
    private String selfText;
    private String name;

    /**
     * Gets the author of the post.
     *
     * @return the username of the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets whether or not the post is marked as NSFW
     *
     * @return true if the post is NSFW, false otherwise
     */
    public boolean isOver18() {
        return over18;
    }

    /**
     * Gets the title of the post.
     *
     * @return the post title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the text for the post, if any.
     *
     * @return the text of the post, or an empty string if the post was a link
     */
    public String getSelfText() {
        return selfText;
    }

    /**
     * Gets the name of the post.
     * <p/>
     * The name is the unique identifier for the post, useful when specifying "before" or "after" in the request.
     *
     * @return
     */
    public String getName() {
        return name;
    }
}
