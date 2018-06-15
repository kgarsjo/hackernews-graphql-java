package com.howtographql.hackernews;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class Scalars {
    public static GraphQLScalarType dateTime = new GraphQLScalarType("DateTime", "DateTime", new Coercing<Object,Object>() {

		@Override
		public String serialize(Object input) {
            try {
                return ((ZonedDateTime)input).format(DateTimeFormatter.ISO_DATE_TIME);
            } catch (ClassCastException e) {
                return null;
            }
			
		}

		@Override
		public String parseValue(Object input) {
			return serialize(input);
		}

		@Override
		public ZonedDateTime parseLiteral(Object input) {
			try {
                return ZonedDateTime.parse(serialize(input));
            } catch (DateTimeException e) {
                return null;
            }
		}
        
    });
}