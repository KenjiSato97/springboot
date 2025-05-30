package com.example.springboot.client;

import com.example.springboot.dtos.CurrencyDataDto;
import com.example.springboot.dtos.ExchangeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ApiCurrencyClient", url = "https://economia.awesomeapi.com.br/json/last")
public interface ApiCurrencyClient {
    @GetMapping("/USD-BRL,EUR-BRL,JPY-BRL")
    ExchangeResponseDto getCurrencyDataDto();
}
