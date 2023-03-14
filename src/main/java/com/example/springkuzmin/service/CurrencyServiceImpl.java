package com.example.springkuzmin.service;

import com.example.springkuzmin.dto.currency.CurrencyRequest;
import com.example.springkuzmin.dto.currency.CurrencyResponse;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService{

    @Override
    public List<CurrencyResponse> findAll() {
        return null;
    }

    @Override
    public CurrencyResponse findById(String currencyName) {
        return null;
    }

    @Override
    public CurrencyResponse update(String currencyName, CurrencyRequest request) {
        return null;
    }

    @Override
    public CurrencyResponse createUser(CurrencyRequest request) {
        return null;
    }

    @Override
    public void delete(String currencyName) {

    }
}
