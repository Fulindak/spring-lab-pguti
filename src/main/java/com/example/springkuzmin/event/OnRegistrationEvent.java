package com.example.springkuzmin.event;

import com.example.springkuzmin.dto.user.RegDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnRegistrationEvent {
    private RegDto regDto;
    public OnRegistrationEvent(RegDto regDto){
        this.regDto = regDto;
    }
}