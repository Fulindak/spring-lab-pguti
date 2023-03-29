package com.example.springkuzmin.service.currency;

import com.example.springkuzmin.dto.currency.CurrencyExchangeRateResponse;
import com.example.springkuzmin.dto.currency.CurrencyRequest;
import com.example.springkuzmin.dto.currency.CurrencyResponse;
import jakarta.validation.constraints.NotNull;


import java.util.List;
import java.util.Date;


public interface CurrencyService {

    @NotNull
    List<CurrencyResponse> findAll();

    @NotNull
    CurrencyResponse findById(@NotNull String currencyName);

    @NotNull
    CurrencyResponse update(@NotNull String currencyName, @NotNull CurrencyRequest request);

    @NotNull
    CurrencyResponse createCurrency(@NotNull CurrencyRequest request);

    void delete(@NotNull String currencyName);

    CurrencyExchangeRateResponse getExchangeRateByDate(@NotNull String currencyName, @NotNull int months);

}
