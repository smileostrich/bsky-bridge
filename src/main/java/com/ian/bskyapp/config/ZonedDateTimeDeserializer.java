package com.ian.bskyapp.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String input = parser.getText();

        if (!input.endsWith("Z"))
            input += "Z";

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(input)
                .withZoneSameInstant(ZoneId.systemDefault());

        return zonedDateTime;
    }

}
