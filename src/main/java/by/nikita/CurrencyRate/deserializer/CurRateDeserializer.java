package by.nikita.CurrencyRate.deserializer;

import by.nikita.CurrencyRate.dto.respond.CurrencyDynamicRate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurRateDeserializer extends JsonDeserializer<CurrencyDynamicRate> {


  @Override
  public CurrencyDynamicRate deserialize(JsonParser jsonParser, DeserializationContext ctxt)
      throws IOException {

    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    JsonNode dateRaw = node.get("Date");
    JsonNode rateRaw = node.get("Cur_OfficialRate");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    LocalDate date = LocalDateTime.parse(dateRaw.asText(), formatter).toLocalDate();
    double rate = rateRaw.asDouble();

    return new CurrencyDynamicRate(date, rate);
  }
}
