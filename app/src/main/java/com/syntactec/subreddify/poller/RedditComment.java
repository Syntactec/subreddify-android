package com.syntactec.subreddify.poller;

/**
 * This class models a Reddit post object.
 */
class RedditComment {
    private String author;
    private boolean over18;
    private String body;
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
     * Gets the body of the comment.
     *
     * @return the text content of the comment.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the name of the comment.
     * <p/>
     * This name is the unique identifier of the comment, useful for specifying things like "before" or "after" when
     * making calls to the API.
     *
     * @return the name of the comment
     */
    public String getName() {
        return name;
    }
}
