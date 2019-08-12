package com.cleaningpartnersapi.overcapacity;

import com.cleaningpartnersapi.overcapacity.dto.InputDto;
import com.cleaningpartnersapi.util.JacksonConverter;
import com.cleaningpartnersapi.util.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OptimizationControllerTest {

    @Autowired
    private JacksonConverter jacksonConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenStructureNullInformed_thenReturnStructureMandatory() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setJunior(6);
        inputDto.setSenior(10);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform structures")));
    }

    @Test
    public void whenStructureEmptyInformed_thenReturnStructureMandatory() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList());
        inputDto.setJunior(6);
        inputDto.setSenior(10);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform structures")));
    }

    @Test
    public void whenSeniorNullInformed_thenReturnSeniorMandatory() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38));
        inputDto.setJunior(6);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform Senior Cleaners")));
    }

    @Test
    public void whenSeniorZeroInformed_thenReturnSeniorMandatory() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38));
        inputDto.setJunior(6);
        inputDto.setSenior(0);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform Senior Cleaners")));
    }

    @Test
    public void whenJuniorNullInformed_thenReturnJuniorMandatory() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38));
        inputDto.setSenior(3);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform Junior Cleaners")));
    }

    @Test
    public void whenJuniorZeroInformed_thenReturnJuniorMandatory() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38));
        inputDto.setSenior(3);
        inputDto.setJunior(0);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform Junior Cleaners")));
    }

    @Test
    public void whenLowNumberOfSeniorInformed_thenReturnMinimalNumberOfSeniorValidation() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38, 35, 23, 53));
        inputDto.setSenior(3);
        inputDto.setJunior(3);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Inform at least one Senior for each structure")));
    }

    @Test
    public void whenNoEnoughSeniorInformed_thenReturnMoreSeniorNeeded() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38, 35, 23, 53));
        inputDto.setSenior(9);
        inputDto.setJunior(5);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("More senior cleaners are needed")));
    }

    @Test
    public void whenNoEnoughJuniorInformed_thenReturnMoreJuniorNeeded() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(38, 35, 23, 53));
        inputDto.setSenior(12);
        inputDto.setJunior(2);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("More junior cleaners are needed")));
    }

    @Test
    public void whenAllDataInformed_thenReturnMinimalOptmization() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(35, 21, 17, 28));
        inputDto.setJunior(6);
        inputDto.setSenior(10);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].senior", is(3)))
                .andExpect(jsonPath("$[0].junior", is(1)))
                .andExpect(jsonPath("$[1].senior", is(2)))
                .andExpect(jsonPath("$[1].junior", is(1)))
                .andExpect(jsonPath("$[2].senior", is(1)))
                .andExpect(jsonPath("$[2].junior", is(0)))
                .andExpect(jsonPath("$[3].senior", is(2)))
                .andExpect(jsonPath("$[3].junior", is(1)));
    }

    @Test
    public void whenMoreRoomsInformed_thenReturnMinimalOptmization() throws Exception {
        InputDto inputDto = new InputDto();
        inputDto.setStructures(Arrays.asList(68, 75, 26, 83));
        inputDto.setSenior(12);
        inputDto.setJunior(11);

        mockMvc.perform(post("/api/optimization")
                .content(jacksonConverter.toJson(inputDto))
                .contentType(Request.getContentType()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].senior", is(3)))
                .andExpect(jsonPath("$[0].junior", is(1)))
                .andExpect(jsonPath("$[1].senior", is(3)))
                .andExpect(jsonPath("$[1].junior", is(1)))
                .andExpect(jsonPath("$[2].senior", is(2)))
                .andExpect(jsonPath("$[2].junior", is(1)))
                .andExpect(jsonPath("$[3].senior", is(3)))
                .andExpect(jsonPath("$[3].junior", is(1)));
    }
}
