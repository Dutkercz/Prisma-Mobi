package com.PrismaMobi.Prisma_Mobi.entities.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = GenderDeserializer.class)
public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    public static Gender fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return OTHER;
        }
        return Gender.valueOf(value.toUpperCase());
    }
}
