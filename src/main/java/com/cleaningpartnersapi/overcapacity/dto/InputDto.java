package com.cleaningpartnersapi.overcapacity.dto;

import lombok.Data;

import java.util.List;

@Data
public class InputDto {

    private List<Integer> structures;
    private Integer senior;
    private Integer junior;

    public void decreaseJunior(Integer value) {
        junior = junior - value;
    }

    public void decreaseSenior(Integer value) {
        senior = senior - value;
    }
}
