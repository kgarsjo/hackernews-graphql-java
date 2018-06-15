package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class LinkResolver implements GraphQLResolver<Link> {
    private final IUserRepository userRepository;

    public LinkResolver(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}