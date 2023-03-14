package com.example.springkuzmin.service;

import com.example.springkuzmin.dto.currency.CurrencyRequest;
import com.example.springkuzmin.dto.currency.CurrencyResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public interface CurrencyService {

    @NotNull
    List<CurrencyResponse> findAll();

    @NotNull
    CurrencyResponse findById(@NotNull String currencyName);

    @NotNull
    CurrencyResponse update(@NotNull String currencyName, @NotNull CurrencyRequest request);

    @NotNull
    CurrencyResponse createUser(@NotNull CurrencyRequest request);

    void delete(@NotNull String currencyName);

}
