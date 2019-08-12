package com.cleaningpartnersapi.overcapacity.controller;


import com.cleaningpartnersapi.overcapacity.dto.InputDto;
import com.cleaningpartnersapi.overcapacity.dto.OutputDto;
import com.cleaningpartnersapi.overcapacity.service.OptimizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/optimization")
public class OptimizationController {

    @Autowired
    private OptimizationService optimizationService;

    @PostMapping
    public List<OutputDto> optimize(@RequestBody InputDto inputDto) {
        return optimizationService.optimize(inputDto);
    }
}
