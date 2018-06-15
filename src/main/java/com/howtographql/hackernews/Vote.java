package com.howtographql.hackernews;

import java.time.ZonedDateTime;

public class Vote {
    private final String id;
    private final ZonedDateTime createdAt;
    private final String linkId;
    private final String userId;

    public Vote(ZonedDateTime createdAt, String linkId, String userId) {
        this(null, createdAt, linkId, userId);
    }

    public Vote(String id, ZonedDateTime createdAt, String linkId, String userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.linkId = linkId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public String getLinkId() {
        return linkId;
    }

    public String getUserId() {
        return userId;
    }
}