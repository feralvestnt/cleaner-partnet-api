package com.cleaningpartnersapi.overcapacity.service;

import com.cleaningpartnersapi.overcapacity.dto.InputDto;
import com.cleaningpartnersapi.overcapacity.dto.OutputDto;

import java.util.List;

public class OptimizationCleaners {

    private Optimization optimization;

    public OptimizationCleaners(Optimization optimizationStrategy) {
        optimization = optimizationStrategy;
    }

    public List<OutputDto> optmizeCleaners(InputDto inputDto) {
        return optimization.optmize(inputDto);
    }

}
