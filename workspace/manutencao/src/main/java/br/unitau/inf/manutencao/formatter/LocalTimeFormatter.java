package br.unitau.inf.manutencao.formatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeFormatter extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree(); // usar JsonNode
        int hour = node.get("hour").asInt();
        int minute = node.get("minute").asInt();
        int second = node.has("second") ? node.get("second").asInt() : 0;
        return LocalTime.of(hour, minute, second);
    }
}
