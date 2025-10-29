package com.example.minimal_prod_backend.config;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

@Configuration
public class GraphQLConfig {

    private static <T> T parseOrThrow(Object input, Function<Object, T> parser, String typeName) {
        try {
            return parser.apply(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse " + typeName + " from input: " + input, e);
        }
    }

    private static OffsetDateTime parseOffsetDateTime(String value) {
        return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private static BigDecimal parseBigDecimal(Object value) {
        if (value instanceof BigDecimal) return (BigDecimal) value;
        if (value instanceof Number) return new BigDecimal(value.toString());
        if (value instanceof String) return new BigDecimal((String) value);
        throw new IllegalArgumentException("Cannot parse BigDecimal from: " + value);
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder
                .scalar(dateTimeScalar())
                .scalar(dateScalar())
                .scalar(numericScalar())
                .scalar(intervalScalar());
    }

    @Bean
    public GraphQLScalarType dateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("Custom scalar for TIMESTAMPTZ / OffsetDateTime")
                .coercing(new Coercing<OffsetDateTime, String>() {
                    @Override
                    public String serialize(@NonNull Object dataFetcherResult,
                                            @NonNull GraphQLContext context,
                                            @NonNull Locale locale) {
                        if (!(dataFetcherResult instanceof OffsetDateTime))
                            throw new CoercingSerializeException("Expected an OffsetDateTime object.");
                        return ((OffsetDateTime) dataFetcherResult).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    }

                    @Override
                    public OffsetDateTime parseValue(@NonNull Object input,
                                                     @NonNull GraphQLContext context,
                                                     @NonNull Locale locale) {
                        if (!(input instanceof String))
                            throw new CoercingParseValueException("Expected a String.");
                        return parseOrThrow(input, v -> parseOffsetDateTime((String) v), "OffsetDateTime");
                    }

                    @Override
                    public OffsetDateTime parseLiteral(@NonNull Value<?> input,
                                                       @NonNull CoercedVariables variables,
                                                       @NonNull GraphQLContext context,
                                                       @NonNull Locale locale) {
                        if (!(input instanceof StringValue))
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        String value = ((StringValue) input).getValue();
                        return parseOrThrow(value, v -> parseOffsetDateTime((String) v), "OffsetDateTime");
                    }

                })
                .build();
    }

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Custom scalar for Date")
                .coercing(new Coercing<java.time.LocalDate, String>() {
                    @Override
                    public String serialize(@NonNull Object dataFetcherResult,
                                            @NonNull GraphQLContext context,
                                            @NonNull Locale locale) {
                        if (!(dataFetcherResult instanceof java.time.LocalDate))
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        return ((java.time.LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                    }

                    @Override
                    public java.time.LocalDate parseValue(@NonNull Object input,
                                                          @NonNull GraphQLContext context,
                                                          @NonNull Locale locale) {
                        if (!(input instanceof String))
                            throw new CoercingParseValueException("Expected a String.");
                        return parseOrThrow(input, v -> java.time.LocalDate.parse((String) v), "LocalDate");
                    }

                    @Override
                    public java.time.LocalDate parseLiteral(@NonNull Value<?> input,
                                                            @NonNull CoercedVariables variables,
                                                            @NonNull GraphQLContext context,
                                                            @NonNull Locale locale) {
                        if (!(input instanceof StringValue))
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        String value = ((StringValue) input).getValue();
                        return parseOrThrow(value, v -> java.time.LocalDate.parse((String) v), "LocalDate");
                    }
                })
                .build();
    }

    @Bean
    public GraphQLScalarType numericScalar() {
        return GraphQLScalarType.newScalar()
                .name("Numeric")
                .description("Custom scalar for BigDecimal")
                .coercing(new Coercing<BigDecimal, String>() {
                    @Override
                    public String serialize(@NonNull Object dataFetcherResult,
                                            @NonNull GraphQLContext context,
                                            @NonNull Locale locale) {
                        if (!(dataFetcherResult instanceof BigDecimal))
                            throw new CoercingSerializeException("Expected a BigDecimal object.");
                        return ((BigDecimal) dataFetcherResult).toPlainString();
                    }

                    @Override
                    public BigDecimal parseValue(@NonNull Object input,
                                                 @NonNull GraphQLContext context,
                                                 @NonNull Locale locale) {
                        return parseOrThrow(input, GraphQLConfig::parseBigDecimal, "BigDecimal");
                    }

                    @Override
                    public BigDecimal parseLiteral(@NonNull Value<?> input,
                                                   @NonNull CoercedVariables variables,
                                                   @NonNull GraphQLContext context,
                                                   @NonNull Locale locale) {
                        if (!(input instanceof StringValue))
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        return parseOrThrow(((StringValue) input).getValue(), GraphQLConfig::parseBigDecimal, "BigDecimal");
                    }
                })
                .build();
    }

    @Bean
    public GraphQLScalarType intervalScalar() {
        return GraphQLScalarType.newScalar()
                .name("Interval")
                .description("Custom scalar for Interval represented as ISO-8601 duration string")
                .coercing(new Coercing<String, String>() {
                    private String validateString(@NonNull Object value) {
                        if (!(value instanceof String))
                            throw new CoercingSerializeException("Expected a String for Interval.");
                        return (String) value;
                    }

                    @Override
                    public String serialize(@NonNull Object dataFetcherResult,
                                            @NonNull GraphQLContext context,
                                            @NonNull Locale locale) {
                        return validateString(dataFetcherResult);
                    }

                    @Override
                    public String parseValue(@NonNull Object input,
                                             @NonNull GraphQLContext context,
                                             @NonNull Locale locale) {
                        return validateString(input);
                    }

                    @Override
                    public String parseLiteral(@NonNull Value<?> input,
                                               @NonNull CoercedVariables variables,
                                               @NonNull GraphQLContext context,
                                               @NonNull Locale locale) {
                        if (!(input instanceof StringValue))
                            throw new CoercingParseLiteralException("Expected a StringValue for Interval.");
                        return ((StringValue) input).getValue();
                    }
                })
                .build();
    }
}
