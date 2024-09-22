package com.example.vacation_calculator;


import com.example.vacation_calculator.controller.VacationPayController;
import com.example.vacation_calculator.service.VacationPayService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  // Изменено на get()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VacationPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculateVacationPayWithDays() throws Exception {
        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "100000")
                        .param("vacationDays", "28"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(100000/29.3*28));
    }

    @Test
    public void testCalculateVacationPayWithDates() throws Exception {
        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "100000")
                        .param("startDate", "2024-05-01")
                        .param("endDate", "2024-05-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(100000 / 29.3 * 6));
    }

    @Test
    public void testCalculateVacationPayWithDatesAndVacationDays() throws Exception {
        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "100000")
                        .param("vacationDays", "20")
                        .param("startDate", "2024-07-01")
                        .param("endDate", "2024-07-14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(100000 / 29.3 * 10));
    }

    @Test
    public void testCalculateVacationPayWithoutRequiredParams() throws Exception {
        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "100000"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateVacationPayWithInvalidSalary() throws Exception {
        mockMvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", "-1000")
                        .param("vacationDays", "28"))
                .andExpect(status().isBadRequest());
    }
}
