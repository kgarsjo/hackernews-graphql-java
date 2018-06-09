package com.howtographql.hackernews;

import org.junit.*;

public class LinkTest {
    private final String description = "Some description";
    private final String id = "1111";
    private final String url = "http://www.example.com";

    private Link link;

    @Before
    public void setUp() {
        link = new Link(id, url, description);
    }

    @Test
    public void getDescriptionReturnsDescription() {
        Assert.assertEquals(description, link.getDescription());
    }

    @Test
    public void getIdReturnsId() {
        Assert.assertEquals(id, link.getId());
    }

    @Test
    public void getUrlReturnsUrl() {
        Assert.assertEquals(url, link.getUrl());
    }
}