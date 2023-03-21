package com.example.springkuzmin.controller;

import com.example.springkuzmin.dto.currency.CurrencyExchangeRateResponse;
import com.example.springkuzmin.dto.currency.CurrencyRequest;
import com.example.springkuzmin.dto.currency.CurrencyResponse;
import com.example.springkuzmin.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {


    @Autowired
    private CurrencyService currencyService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<CurrencyResponse> findAll() {
        return currencyService.findAll();
    }

    @GetMapping(value = "/{currencyName}", produces = APPLICATION_JSON_VALUE)
    public CurrencyResponse findById(@PathVariable String currencyName) {
        return currencyService.findById(currencyName);
    }

    @GetMapping(value = "/{currencyName}/{months}", produces = APPLICATION_JSON_VALUE)
    public CurrencyExchangeRateResponse getExchangeRateByDate(@PathVariable String currencyName, @PathVariable int months) {
        return currencyService.getExchangeRateByDate(currencyName, months);
    }

    @PostMapping(value = "/{currencyName}", produces = APPLICATION_JSON_VALUE)
    public CurrencyResponse update(@PathVariable String currencyName, @RequestBody CurrencyRequest request) {
     return currencyService.update(currencyName, request);
    }
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public CurrencyResponse create(@RequestBody CurrencyRequest request) {
        return currencyService.createCurrency(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{currencyName}")
    public void delete(@PathVariable String currencyName) {
        currencyService.delete(currencyName);
    }
}
