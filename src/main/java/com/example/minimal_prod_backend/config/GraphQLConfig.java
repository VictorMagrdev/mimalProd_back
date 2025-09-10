package com.example.minimal_prod_backend.config;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(dateTimeScalar());
    }

    @Bean
    public GraphQLScalarType dateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("A custom scalar that handles LocalDateTime")
                .coercing(new Coercing<LocalDateTime, String>() {
                    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    @Override
                    public String serialize(Object dataFetcherResult,
                                            GraphQLContext context,
                                            Locale locale) throws CoercingSerializeException {
                        try {
                            if (dataFetcherResult instanceof LocalDateTime) {
                                return ((LocalDateTime) dataFetcherResult).format(formatter);
                            }
                            throw new CoercingSerializeException("Expected a LocalDateTime object.");
                        } catch (RuntimeException e) {
                            throw new CoercingSerializeException("Unable to serialize LocalDateTime", e);
                        }
                    }

                    @Override
                    public LocalDateTime parseValue(Object input,
                                                    GraphQLContext context,
                                                    Locale locale) throws CoercingParseValueException {
                        try {
                            if (input instanceof String) {
                                return LocalDateTime.parse((String) input, formatter);
                            }
                            throw new CoercingParseValueException("Expected a String.");
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException("Not a valid ISO_LOCAL_DATE_TIME: " + input, e);
                        } catch (RuntimeException e) {
                            throw new CoercingParseValueException("Unable to parse value for LocalDateTime", e);
                        }
                    }

                    @Override
                    public LocalDateTime parseLiteral(Value<?> input,
                                                      CoercedVariables variables,
                                                      GraphQLContext context,
                                                      Locale locale) throws CoercingParseLiteralException {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDateTime.parse(((StringValue) input).getValue(), formatter);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException("Not a valid ISO_LOCAL_DATE_TIME: " + input, e);
                            } catch (RuntimeException e) {
                                throw new CoercingParseLiteralException("Unable to parse literal for LocalDateTime", e);
                            }
                        }
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                })
                .build();
    }
}
