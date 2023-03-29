package com.example.springkuzmin.repository;

import com.example.springkuzmin.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepos extends JpaRepository<Currency, String> {

}
