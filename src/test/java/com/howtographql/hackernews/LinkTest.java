package com.howtographql.hackernews;

import org.junit.*;

public class LinkTest {
    private final String description = "Some description";
    private final String url = "http://www.example.com";

    private Link link;

    @Before
    public void setUp() {
        link = new Link(url, description);
    }

    @Test
    public void getDescriptionReturnsDescription() {
        Assert.assertEquals(link.getDescription(), description);
    }

    @Test
    public void getUrl() {
        Assert.assertEquals(link.getUrl(), url);
    }
}