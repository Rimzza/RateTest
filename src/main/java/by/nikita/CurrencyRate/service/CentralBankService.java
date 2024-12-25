package by.nikita.CurrencyRate.service;

import by.nikita.CurrencyRate.dto.request.Currency;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CentralBankService {

  private final RestTemplate restTemplate = new RestTemplate();

  private final String UrlAllCur = "https://api.nbrb.by/exrates/currencies";

  private static final ZoneId ZONE_ID = ZoneId.of("Europe/Minsk");

  private final String UrlDynamic = "https://api.nbrb.by/ExRates/Rates/Dynamics/%d?startDate=%s&endDate=%s";


  public ResponseEntity<String> getAvailableCurrency() {
    return restTemplate.getForEntity(UrlAllCur, String.class);
  }

  public ResponseEntity<String> getRateDynamics(Currency currency, Instant dateFrom,
      Instant dateTo) {
    LocalDate localDateTo = LocalDate.ofInstant(dateTo, ZONE_ID);
    LocalDate localDateFrom = LocalDate.ofInstant(dateFrom, ZONE_ID);
    String url = String.format(UrlDynamic, currency.getInfo().getCurrencyId(), localDateFrom,
        localDateTo);
    return restTemplate.getForEntity(url, String.class);
  }

}
