package com.cleaningpartnersapi.overcapacity.service;

import com.cleaningpartnersapi.overcapacity.dto.InputDto;
import com.cleaningpartnersapi.overcapacity.dto.OutputDto;

import java.util.List;

public interface Optimization {

    List<OutputDto> optmize(InputDto inputDto);
}
