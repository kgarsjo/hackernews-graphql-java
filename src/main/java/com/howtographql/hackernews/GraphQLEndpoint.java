package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import graphql.servlet.SimpleGraphQLServlet;
import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final long serialVersionUID = 3936594434298527600L;

  private static final ILinkRepository linkRepository;

  static {
    MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
    linkRepository = new LinkMongodbRepository(mongo.getCollection("links"));
  }

  public GraphQLEndpoint() {
    super(buildSchema());
  }

  private static GraphQLSchema buildSchema() {
    Query query = new Query(linkRepository);
    Mutation mutation = new Mutation(linkRepository);
    
    return SchemaParser.newParser()
      .file("schema.graphqls")
      .resolvers(query, mutation)
      .build()
      .makeExecutableSchema();
  }
}
