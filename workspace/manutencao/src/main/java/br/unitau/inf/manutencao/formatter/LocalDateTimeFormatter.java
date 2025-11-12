package br.unitau.inf.manutencao.formatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node.isTextual()) {
            String text = node.asText().trim();
            if (text.isEmpty()) {
                return null;
            }

            try {
                return LocalDateTime.parse(text, FORMATTER);
            } catch (Exception e) {
                throw new IOException("Formato inválido para data/hora. Use 'dd/MM/yyyy HH:mm:ss'. Valor recebido: " + text);
            }
        }

        int year = node.has("year") ? node.get("year").asInt() : LocalDateTime.now().getYear();
        int month = node.has("month") ? node.get("month").asInt() : 1;
        int day = node.has("day") ? node.get("day").asInt() : 1;
        int hour = node.has("hour") ? node.get("hour").asInt() : 0;
        int minute = node.has("minute") ? node.get("minute").asInt() : 0;
        int second = node.has("second") ? node.get("second").asInt() : 0;

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
