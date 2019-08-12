package com.cleaningpartnersapi.overcapacity.service;

import com.cleaningpartnersapi.comum.validation.ValidationException;
import com.cleaningpartnersapi.overcapacity.dto.InputDto;
import com.cleaningpartnersapi.overcapacity.dto.OutputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptimizationService {

    public List<OutputDto> optimize(InputDto inputDto) {
        inputValidation(inputDto);
        return new OptimizationCleaners(new OptimizationMinimal()).optmizeCleaners(inputDto);
    }

    private void inputValidation(InputDto inputDto) {
        if (inputDto.getStructures() == null || inputDto.getStructures().size() == 0) {
            throw new ValidationException("Inform structures");
        }
        if (inputDto.getSenior() == null || inputDto.getSenior() == 0) {
            throw new ValidationException("Inform Senior Cleaners");
        }
        if (inputDto.getJunior() == null || inputDto.getJunior() == 0) {
            throw new ValidationException("Inform Junior Cleaners");
        }
        if (inputDto.getStructures().size() > inputDto.getSenior()) {
            throw new ValidationException("Inform at least one Senior for each structure");
        }
    }
}
