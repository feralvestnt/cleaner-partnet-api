package com.cleaningpartnersapi.overcapacity;


import com.cleaningpartnersapi.overcapacity.service.OptimizationMinimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptimizationMinimalTest {

    @Test
    public void whenMinimalLevel_thenReturnSmallStructure() {
        int structureRooms = 15;

        OptimizationMinimal optimizationMinimal = new OptimizationMinimal();
        String structureLevel = optimizationMinimal.getLevelStructure(structureRooms);

        Assert.assertEquals("SMALL_STRUCTURE", structureLevel);
    }

    @Test
    public void whenMediumLevel_thenReturnMediumStructure() {
        int structureRooms = 25;

        OptimizationMinimal optimizationMinimal = new OptimizationMinimal();
        String structureLevel = optimizationMinimal.getLevelStructure(structureRooms);

        Assert.assertEquals("MEDIUM_STRUCTURE", structureLevel);
    }

    @Test
    public void whenLargeLevel_thenReturnBigStructure() {
        int structureRooms = 35;

        OptimizationMinimal optimizationMinimal = new OptimizationMinimal();
        String structureLevel = optimizationMinimal.getLevelStructure(structureRooms);

        Assert.assertEquals("BIG_STRUCTURE", structureLevel);
    }





}
