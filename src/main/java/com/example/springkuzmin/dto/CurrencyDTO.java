package com.example.springkuzmin.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CurrencyDTO {
    private String name;
    private Long quantity;
    private Double exchangeRate;
    private Date date;
}
