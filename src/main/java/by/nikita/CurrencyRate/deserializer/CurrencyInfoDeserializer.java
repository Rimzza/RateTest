package by.nikita.CurrencyRate.deserializer;

import by.nikita.CurrencyRate.dto.request.CurrencyInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyInfoDeserializer extends JsonDeserializer<CurrencyInfo> {

  @Override
  public CurrencyInfo deserialize(JsonParser jsonParser, DeserializationContext ctxt)
      throws IOException {

    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    JsonNode nameRaw = node.get("Cur_Name");
    JsonNode idRaw = node.get("Cur_ID");
    JsonNode dateRaw = node.get("Cur_DateEnd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    LocalDateTime dateTime = LocalDateTime.parse(dateRaw.asText(), formatter);
    if(LocalDateTime.now().isAfter(dateTime)){
      return null;
    }

    String name = nameRaw.asText();
    int id = idRaw.asInt();

    return new CurrencyInfo(name, id);
  }
}
