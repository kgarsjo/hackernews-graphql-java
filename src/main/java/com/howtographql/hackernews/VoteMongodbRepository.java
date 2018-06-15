package com.howtographql.hackernews;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class VoteMongodbRepository implements IVoteRepository {
    private final MongoCollection<Document> votes;

    public VoteMongodbRepository(MongoCollection<Document> votes) {
        this.votes = votes;
    }

	@Override
	public List<Vote> findByUserId(String userId) {
        List<Vote> list = new ArrayList<>();
        for (Document doc : votes.find(eq("userId", userId))) {
            list.add(vote(doc));
        }
        return list;
	}

	@Override
	public List<Vote> findByLinkId(String linkId) {
		List<Vote> list = new ArrayList<>();
        for (Document doc : votes.find(eq("linkId", linkId))) {
            list.add(vote(doc));
        }
        return list;
	}

	@Override
	public Vote saveVote(Vote vote) {
        Document doc = new Document()
            .append("createdAt", Scalars.dateTime.getCoercing().serialize(vote.getCreatedAt()))
            .append("linkId", vote.getLinkId())
            .append("userId", vote.getUserId());
        votes.insertOne(doc);
        return vote(doc);
    }
    
    private Vote vote(Document doc) {
        return new Vote(
            doc.get("_id").toString(),
            ZonedDateTime.parse(doc.getString("createdAt")),
            doc.getString("linkId"),
            doc.getString("userId")
        );
    }
    
}