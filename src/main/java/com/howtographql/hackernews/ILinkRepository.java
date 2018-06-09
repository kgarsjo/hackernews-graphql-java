package com.howtographql.hackernews;

import java.util.List;

public interface ILinkRepository {
    public Link findById(String id);

    public List<Link> getAllLinks();

    public void saveLink(Link link);
}