package by.nikita.CurrencyRate.dto.respond;

import by.nikita.CurrencyRate.deserializer.CurRateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;



@JsonDeserialize(using = CurRateDeserializer.class)
public record CurrencyDynamicRate(LocalDate date, double rate) {

}
