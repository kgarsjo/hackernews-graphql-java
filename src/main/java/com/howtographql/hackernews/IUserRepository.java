package com.howtographql.hackernews;

public interface IUserRepository {
    public User findByEmail(String email);

    public User findById(String id);

    public User saveUser(User user);
}