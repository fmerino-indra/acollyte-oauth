package org.fmm.acollyte.common.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import jakarta.persistence.AttributeConverter;

@Deprecated
//@Converter(autoApply = true)
public class OffsetDateTimeAttributeConverter implements AttributeConverter<OffsetDateTime, LocalDateTime> {

    public LocalDateTime convertToDatabaseColumn(OffsetDateTime attribute) {
        return attribute.toLocalDateTime();
    }

    public OffsetDateTime convertToEntityAttribute(LocalDateTime dbData) {
        return dbData.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

}
