package com.example.springkuzmin.service;

import com.example.springkuzmin.dto.currency.CurrencyExchangeRateResponse;
import com.example.springkuzmin.dto.currency.CurrencyRequest;
import com.example.springkuzmin.dto.currency.CurrencyResponse;
import com.example.springkuzmin.model.Currency;
import com.example.springkuzmin.repository.CurrencyRepository;
import com.example.springkuzmin.util.DateUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{

    @Autowired
    private CurrencyRepository currencyRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<CurrencyResponse> findAll() {
        return currencyRepository.findAll()
                .stream()
                .map(this::buildCurrencyResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public CurrencyResponse findById(String currencyName) {
        return currencyRepository.findById(currencyName)
                .map(this::buildCurrencyResponse)
                .orElseThrow(() -> new EntityNotFoundException("Currency" + currencyName + "is not found"));
    }

    @NotNull
    private CurrencyResponse buildCurrencyResponse(@NotNull Currency currency) {
        return new CurrencyResponse()
                .setName(currency.getName())
                .setDate(currency.getDate())
                .setQuantity(currency.getQuantity())
                .setExchangeRate(currency.getExchangeRate());
    }

    @NotNull
    @Override
    @Transactional
    public CurrencyResponse update(String currencyName, CurrencyRequest request) {
        Currency currency = currencyRepository.findById(currencyName)
                .orElseThrow(() -> new EntityNotFoundException("Currency" + currencyName + "is not found"));
        currencyUpdate(currency, request);
        return buildCurrencyResponse(currencyRepository.save(currency));
    }

    @NotNull
    private void currencyUpdate(@NotNull Currency currency, @NotNull CurrencyRequest request) {
        ofNullable(request.getName()).map(currency::setName);
        ofNullable(request.getDate()).map(currency::setDate);
        ofNullable(request.getQuantity()).map(currency::setQuantity);
        ofNullable(request.getExchangeRate()).map(request::setExchangeRate);
    }

    @NotNull
    @Override
    @Transactional
    public CurrencyResponse createCurrency(CurrencyRequest request) {
        Currency currency = buildCurrencyRequest(request);
        return buildCurrencyResponse(currencyRepository.save(currency));
    }

    @Override
    public void delete(String currencyName) {
        currencyRepository.deleteById(currencyName);
    }

    @NotNull
    private Currency buildCurrencyRequest(CurrencyRequest request) {
        return new Currency()
                .setName(request.getName())
                .setDate(request.getDate())
                .setQuantity(request.getQuantity())
                .setExchangeRate(request.getExchangeRate());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public CurrencyExchangeRateResponse getExchangeRateByDate(String currencyName, int months) {
        return buildCurrencyExchangeRateResponse(currencyRepository.findById(currencyName)
                .orElseThrow(() -> new EntityNotFoundException("Currency" + currencyName + "is not found"))
        , months);

    }

    @NotNull
    private CurrencyExchangeRateResponse buildCurrencyExchangeRateResponse(@NotNull Currency currency, @NotNull int months) {
        return new CurrencyExchangeRateResponse()
                .setName(currency.getName())
                .setExchangeRate(newExchangeRate(currency, months))
                .setDate(DateUtil.addMonth(currency.getDate(), months));
    }

    private Double newExchangeRate(Currency currency, int months) {
        Double exchangeRate = currency.getExchangeRate();
        if (months > 0) {
            for (int i = 0; i < months; i++) {
                exchangeRate = exchangeRate + exchangeRate * 0.05;
            }
        }
        if (months < 0) {
            for (int i = 0; i < (months * (-1)); i++) {
                exchangeRate = exchangeRate - exchangeRate * 0.05;
            }
        }
        return exchangeRate;
    }


}
