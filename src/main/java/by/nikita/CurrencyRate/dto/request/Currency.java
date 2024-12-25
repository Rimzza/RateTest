package by.nikita.CurrencyRate.dto.request;

import java.util.Date;
import lombok.Getter;

import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Currency {

  private  CurrencyInfo info;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private  Date dateFrom;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private  Date dateTo;



}
