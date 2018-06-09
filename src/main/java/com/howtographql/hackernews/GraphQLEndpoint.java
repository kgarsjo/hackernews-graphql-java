package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;
import graphql.servlet.SimpleGraphQLServlet;
import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final long serialVersionUID = 3936594434298527600L;

  public GraphQLEndpoint() {
    super(buildSchema());
  }

  private static GraphQLSchema buildSchema() {
    LinkRepository linkRepository = new LinkRepository();
    Query query = new Query(linkRepository);
    Mutation mutation = new Mutation(linkRepository);
    
    return SchemaParser.newParser()
      .file("schema.graphqls")
      .resolvers(query, mutation)
      .build()
      .makeExecutableSchema();
  }
}
