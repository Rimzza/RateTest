package by.nikita.CurrencyRate.dto.request;

import by.nikita.CurrencyRate.deserializer.CurrencyInfoDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize(using = CurrencyInfoDeserializer.class)
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyInfo {

  @JsonProperty(value = "Cur_Name")
  private String currencyName;

  @JsonProperty(value = "Cur_ID")
  private int currencyId;
}
