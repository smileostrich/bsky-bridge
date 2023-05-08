package com.ian.bskyapp.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ZonedDateTime zonedDateTime = value.withZoneSameInstant(ZoneId.systemDefault());
        gen.writeString(zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

}
