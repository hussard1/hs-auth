package com.hussard.hsauth.domain.converter;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class HttpMethodConverter implements AttributeConverter<Set<HttpMethod>, String> {

    @Override
    public String convertToDatabaseColumn(Set<HttpMethod> httpMethods) {
        return StringUtils.collectionToCommaDelimitedString(httpMethods);
    }

    @Override
    public Set<HttpMethod> convertToEntityAttribute(String dbData) {
        return Arrays.stream(StringUtils.commaDelimitedListToStringArray(dbData))
                .map(HttpMethod::resolve).collect(Collectors.toSet());
    }
}
