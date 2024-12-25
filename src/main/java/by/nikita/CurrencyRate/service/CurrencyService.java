package by.nikita.CurrencyRate.service;

import by.nikita.CurrencyRate.dto.request.Currency;
import by.nikita.CurrencyRate.dto.request.CurrencyInfo;
import by.nikita.CurrencyRate.dto.respond.CurrencyDynamicRate;
import by.nikita.CurrencyRate.util.ResponceMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {

  private final RestTemplate restTemplate = new RestTemplate();

  private final CentralBankService service;

  public List<CurrencyInfo> getAvailableCurrency() throws JsonProcessingException {
    ResponseEntity<String> response = service.getAvailableCurrency();
    List<CurrencyInfo> infos = ResponceMapper.toListCurrencyInfo(response.getBody());
    return infos;
  }


  public List<List<Object>> dynamicsCurrencies(Currency currency) {
    Instant dateFrom = currency.getDateFrom().toInstant();
    Instant dateTo = currency.getDateTo().toInstant();
    /*ZoneId zoneId = ZoneId.of("Europe/Minsk");
    long diffInDays = ChronoUnit.DAYS.between(dateFrom, dateTo);*/
    ResponseEntity<String> response = service.getRateDynamics(currency, dateFrom, dateTo);
    List<CurrencyDynamicRate> rates = ResponceMapper.toListCurrencyDynamicRate(response.getBody());
    return test(rates);
  }

  private List<List<Object>> test(List<CurrencyDynamicRate> rateList) {
    int x = 0;
    List<List<Object>> result = new ArrayList<>();
    for (CurrencyDynamicRate rate : rateList) {
      result.add(List.of(rate.date(), rate.rate()));
    }
    return result;
  }
}

