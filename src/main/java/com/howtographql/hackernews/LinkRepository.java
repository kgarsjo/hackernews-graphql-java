package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.List;

public class LinkRepository {
  private final List<Link> links;

  public LinkRepository() {
    links = new ArrayList<Link>();
    links.add(new Link("http://howtographql.com", "Foo"));
    links.add(new Link("http://graphql.org/learn", "Bar"));
  }

  public List<Link> getAllLinks() {
    return links;
  }

  public void saveLink(Link link) {
    links.add(link);
  }
}
