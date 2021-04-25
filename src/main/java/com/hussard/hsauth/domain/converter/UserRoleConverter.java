package com.hussard.hsauth.domain.converter;

import com.hussard.hsauth.domain.enums.AuthorityType;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRoleConverter implements AttributeConverter<Set<AuthorityType>, String> {

    @Override
    public String convertToDatabaseColumn(Set<AuthorityType> userRole) {
        return StringUtils.collectionToCommaDelimitedString(userRole);
    }

    @Override
    public Set<AuthorityType> convertToEntityAttribute(String dbData) {
        return Arrays.stream(StringUtils.commaDelimitedListToStringArray(dbData))
                .map(AuthorityType::of).collect(Collectors.toSet());
    }
}
