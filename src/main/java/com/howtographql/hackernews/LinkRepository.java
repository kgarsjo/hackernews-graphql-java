package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.List;

public class LinkRepository {
  private final List<Link> links;

  public LinkRepository() {
    this(new ArrayList<Link>());
  }

  public LinkRepository(List<Link> links) {
    this.links = links;
  }

  public List<Link> getAllLinks() {
    return links;
  }

  public void saveLink(Link link) {
    links.add(link);
  }
}
