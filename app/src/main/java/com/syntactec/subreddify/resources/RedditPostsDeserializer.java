package com.syntactec.subreddify.resources;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RedditPostsDeserializer implements JsonDeserializer<List<RedditPost>> {
    @Override
    public List<RedditPost> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        List<RedditPost> posts = new ArrayList<>();

        JsonArray children = jsonElement.getAsJsonObject()
                .get("data")
                .getAsJsonObject()
                .get("children")
                .getAsJsonArray();

        for (JsonElement postElement : children) {
            RedditPost post = context.deserialize(postElement.getAsJsonObject().get("data"), RedditPost.class);
            posts.add(post);
        }

        return posts;
    }
}
