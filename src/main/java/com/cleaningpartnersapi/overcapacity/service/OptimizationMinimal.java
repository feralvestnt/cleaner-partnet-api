package com.cleaningpartnersapi.overcapacity.service;

import com.cleaningpartnersapi.comum.validation.ValidationException;
import com.cleaningpartnersapi.overcapacity.dto.InputDto;
import com.cleaningpartnersapi.overcapacity.dto.OutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptimizationMinimal implements Optimization {

    private static String SMALL_STRUCTURE = "SMALL_STRUCTURE";
    private static String MEDIUM_STRUCTURE = "MEDIUM_STRUCTURE";
    private static String BIG_STRUCTURE = "BIG_STRUCTURE";

    public List<OutputDto> optmize(InputDto inputDto) {
        List<OutputDto> outputDtoList = new ArrayList<>();

        for (Integer structureRooms : inputDto.getStructures()) {
            String levelStructure = getLevelStructure(structureRooms);

            decreaseInputDto(levelStructure, inputDto);

            validationInputDto(inputDto);

            outputDtoList.add(getOutputDto(levelStructure, inputDto));
        }
        return outputDtoList;
    }

    public String getLevelStructure(Integer structureRomms) {
        if (structureRomms < 20) {
            return SMALL_STRUCTURE;
        } else if (structureRomms > 20 && structureRomms < 30) {
            return MEDIUM_STRUCTURE;
        } else {
            return BIG_STRUCTURE;
        }
    }

    public void decreaseInputDto(String levelStructure, InputDto inputDto) {
        if (levelStructure.equals(SMALL_STRUCTURE)) {
            inputDto.decreaseSenior(1);
        } else if (levelStructure.equals(MEDIUM_STRUCTURE)) {
            inputDto.decreaseSenior(2);
            inputDto.decreaseJunior(1);
        } else if (levelStructure.equals(BIG_STRUCTURE)) {
            inputDto.decreaseSenior(3);
            inputDto.decreaseJunior(1);
        }
    }

    public void validationInputDto(InputDto inputDto) {
        if (inputDto.getJunior() < 0) {
            throw new ValidationException("More junior cleaners are needed");
        }
        if (inputDto.getSenior() < 0) {
            throw new ValidationException("More senior cleaners are needed");
        }
    }

    public OutputDto getOutputDto(String levelStructure, InputDto inputDto) {
        OutputDto outputDto = new OutputDto();
        if (levelStructure.equals(SMALL_STRUCTURE)) {
            outputDto.setSenior(1);
            outputDto.setJunior(0);
        } else if (levelStructure.equals(MEDIUM_STRUCTURE)) {
            outputDto.setSenior(2);
            outputDto.setJunior(1);
        } else if (levelStructure.equals(BIG_STRUCTURE)) {
            outputDto.setSenior(3);
            outputDto.setJunior(1);
        }
        return outputDto;
    }

}
