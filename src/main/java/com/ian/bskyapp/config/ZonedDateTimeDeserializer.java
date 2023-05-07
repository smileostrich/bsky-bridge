package com.ian.bskyapp.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String input = parser.getText();
        ZonedDateTime zonedDateTime;

        if (!input.endsWith("Z")) {
            LocalDateTime localDateTime = LocalDateTime.parse(input);
            zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        } else {
            zonedDateTime = ZonedDateTime.parse(input, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    .withZoneSameInstant(ZoneId.systemDefault());
        }

        return zonedDateTime;
    }

}
