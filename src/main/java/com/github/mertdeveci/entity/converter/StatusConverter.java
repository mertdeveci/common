package com.github.mertdeveci.entity.converter;

import com.github.mertdeveci.enums.EnumUtils;
import com.github.mertdeveci.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return Optional.ofNullable(status).map(Status::getValue).orElse( null);
    }

    @Override
    public Status convertToEntityAttribute(Integer value) {
        return EnumUtils.getValueEnum(Status.class, value);
    }
}
