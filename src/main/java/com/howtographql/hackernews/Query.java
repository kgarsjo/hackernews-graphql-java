package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import java.util.List;

public class Query implements GraphQLRootResolver {
  private final ILinkRepository linkRepository;

  public Query(ILinkRepository linkRepository) {
    this.linkRepository = linkRepository;
  }

  public List<Link> allLinks() {
    return linkRepository.getAllLinks();
  }
}
