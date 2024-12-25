package by.nikita.CurrencyRate.controller;

import by.nikita.CurrencyRate.dto.request.Currency;
import by.nikita.CurrencyRate.dto.request.CurrencyInfo;
import by.nikita.CurrencyRate.service.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(("/currency"))
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyService service;

  private List<CurrencyInfo> currencyList;

  @GetMapping
  public String getCurrencyForm(Model model, @ModelAttribute("currency") Currency currency,
      @ModelAttribute("currencyInfo") CurrencyInfo currencyInfo)
      throws JsonProcessingException {
    if (currencyList == null) {
      currencyList = service.getAvailableCurrency();
    }
    model.addAttribute("currencyInfoList", currencyList);
    model.addAttribute("maxDate", LocalDate.now());
    return "newCurrency";
  }

  @PostMapping
  public String create(Model model, @ModelAttribute("currency") Currency currency) {
    List<List<Object>> rateList = service.dynamicsCurrencies(currency);
    model.addAttribute("rateList", rateList);
    return "rateDynamics";
  }
}
