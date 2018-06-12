package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graphql.servlet.GraphQLServletListener;
import graphql.servlet.SimpleGraphQLServlet;
import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final long serialVersionUID = 3936594434298527600L;

  private static final ILinkRepository linkRepository;
  private static final IUserRepository userRepository;

  static {
    MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
    linkRepository = new LinkMongodbRepository(mongo.getCollection("links"));
    userRepository = new UserMongodbRepository(mongo.getCollection("users"));
  }

  public GraphQLEndpoint() {
    super(buildSchema());
    this.addListener(new GraphQLServletListener() {
      @Override
      public GraphQLServletListener.RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        return new GraphQLServletListener.RequestCallback() {
            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
              java.lang.System.out.print(throwable.toString());
            }
        };
    }
    });
  }

  private static GraphQLSchema buildSchema() {
    Query query = new Query(linkRepository);
    Mutation mutation = new Mutation(linkRepository, userRepository);
    
    return SchemaParser.newParser()
      .file("schema.graphqls")
      .resolvers(query, mutation)
      .build()
      .makeExecutableSchema();
  }
}
