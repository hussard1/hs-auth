package com.hussard.hsauth.domain.converter;

import com.hussard.hsauth.domain.enums.UserRole;
import org.springframework.boot.convert.Delimiter;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRoleConverter implements AttributeConverter<Set<UserRole>, String> {

    @Override
    public String convertToDatabaseColumn(Set<UserRole> userRole) {
        return StringUtils.collectionToCommaDelimitedString(userRole);
    }

    @Override
    public Set<UserRole> convertToEntityAttribute(String dbData) {
        return Arrays.stream(StringUtils.commaDelimitedListToStringArray(dbData))
                .map(UserRole::of).collect(Collectors.toSet());
    }
}
