package com.howtographql.hackernews;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class UserMongodbRepository implements IUserRepository {
    private final MongoCollection<Document> users;

    public UserMongodbRepository(MongoCollection<Document> users) {
        this.users = users;
    }

	@Override
	public User findByEmail(String email) {
        Document doc = users.find(eq("email", email)).first();
        return user(doc);
	}

	@Override
	public User findById(String id) {
		Document doc = users.find(eq("_id", new ObjectId(id))).first();
        return user(doc);
	}

	@Override
	public User saveUser(User user) {
        Document doc = new Document()
            .append("name", user.getName())
            .append("email", user.getEmail())
            .append("password", user.getPassword());
        users.insertOne(doc);

		return user(doc);
    }
    
    private User user(Document doc) {
        if (doc == null) {
            return null;
        }
        return new User(
            doc.get("_id").toString(),
            doc.getString("name"),
            doc.getString("email"),
            doc.getString("password")
        );
    }
}