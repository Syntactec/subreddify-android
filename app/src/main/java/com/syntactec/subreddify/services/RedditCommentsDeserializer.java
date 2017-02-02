package com.syntactec.subreddify.services;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RedditCommentsDeserializer implements JsonDeserializer<List<RedditComment>> {
    @Override
    public List<RedditComment> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        List<RedditComment> posts = new ArrayList<>();

        JsonArray children = jsonElement.getAsJsonObject()
                .get("data")
                .getAsJsonObject()
                .get("children")
                .getAsJsonArray();

        for (JsonElement postElement : children) {
            RedditComment post = context.deserialize(postElement, RedditComment.class);
            posts.add(post);
        }

        return posts;
    }
}
