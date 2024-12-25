package by.nikita.CurrencyRate.util;

import by.nikita.CurrencyRate.dto.request.CurrencyInfo;
import by.nikita.CurrencyRate.dto.respond.CurrencyDynamicRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResponceMapper {

  public static List<CurrencyInfo> toListCurrencyInfo(String s) {
    ObjectMapper objectMapper = new ObjectMapper();

    List<CurrencyInfo> infos = null;
    try {
      infos = objectMapper.readValue(s,
          new TypeReference<List<CurrencyInfo>>() {
          });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    infos.removeIf(Objects::isNull);
    return infos;
  }

  public static List<CurrencyDynamicRate> toListCurrencyDynamicRate(String s) {
    ObjectMapper objectMapper = new ObjectMapper();
    List<List<Object>> result = new ArrayList<>();
    List<CurrencyDynamicRate> rates = null;
    try {
      rates = objectMapper.readValue(s,
          new TypeReference<List<CurrencyDynamicRate>>() {
          });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return rates;

  }

}
