package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class VoteResolver implements GraphQLResolver<Vote> {
    private final ILinkRepository linkRepository;
    private final IUserRepository userRepository;

    public VoteResolver(ILinkRepository linkRepository, IUserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public Link link(Vote vote) {
        return linkRepository.findById(vote.getLinkId());
    
    }

    public User user(Vote vote) {
        return userRepository.findById(vote.getUserId());
    }

}