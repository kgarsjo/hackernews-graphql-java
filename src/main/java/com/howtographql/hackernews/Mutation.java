package com.howtographql.hackernews;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {
    private final ILinkRepository linkRepository;
    private final IUserRepository userRepository;
    private final IVoteRepository voteRepository;

    public Mutation(ILinkRepository linkRepository, IUserRepository userRepository, IVoteRepository voteRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Link createLink(String url, String description, DataFetchingEnvironment env) {
        AuthContext authContext = env.getContext();
        Link link = new Link(url, description, authContext.getUser().getId());
        linkRepository.saveLink(link);
        return link;
    }

    public User createUser(String name, AuthData auth) {
        User newUser = new User(name, auth.getEmail(), auth.getPassword());
        userRepository.saveUser(newUser);
        return newUser;
    }

    public Vote createVote(String linkId, String userId) {
        ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
        Vote vote = new Vote(now, linkId, userId);
        return voteRepository.saveVote(vote);
    }

    public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
        User user = userRepository.findByEmail(auth.getEmail());
        if (user == null) {
            throw new GraphQLException("Invalid credentials");
        }
        if (!user.getPassword().equals(auth.getPassword())) {
            throw new GraphQLException("Invalid Credentials");
        }
        return new SigninPayload(user.getId(), user);
    }
}