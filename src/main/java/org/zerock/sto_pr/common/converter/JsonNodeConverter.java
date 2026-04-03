package org.zerock.sto_pr.common.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    // Java -> DB 저장할 때 호출 (JsonNode를 String으로)
    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {

        if (attribute == null) return null;

        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("JSON 직렬화 실패", e);
        }
    }

    // DB -> Java 읽을 때 호출 (String을 JsonNode로)
    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
       if (dbData == null) return null;

       try {
           return mapper.readTree(dbData);
       } catch (Exception e) {
           throw new RuntimeException("JSON 역직렬화 실패", e);
       }
    }
}
