package com.howtographql.hackernews;

import java.util.ArrayList;
import java.util.List;

public class LinkMemoryRepository implements ILinkRepository {
  private final List<Link> links;

  public LinkMemoryRepository() {
    this(new ArrayList<Link>());
  }

  public LinkMemoryRepository(List<Link> links) {
    this.links = links;
  }

  @Override
  public Link findById(String id) {
    throw new UnsupportedOperationException();
  }

  public List<Link> getAllLinks() {
    return links;
  }

  public void saveLink(Link link) {
    links.add(link);
  }
}
