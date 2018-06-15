package com.howtographql.hackernews;

import java.time.ZonedDateTime;
import java.util.List;

public interface IVoteRepository {
    public List<Vote> findByUserId(String userId);
    public List<Vote> findByLinkId(String linkId);
    public Vote saveVote(Vote vote);
}