package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLRootResolver {
    private final ILinkRepository linkRepository;
    private final IUserRepository userRepository;

    public Mutation(ILinkRepository linkRepository, IUserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
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