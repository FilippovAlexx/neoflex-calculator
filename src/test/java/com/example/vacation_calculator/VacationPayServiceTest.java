package com.example.vacation_calculator;

import com.example.vacation_calculator.service.HolidayService;
import com.example.vacation_calculator.service.VacationPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class VacationPayServiceTest {

    @Autowired
    private VacationPayService vacationPayService;


    @Test
    public void testCalculateVacationPayWithoutDates() {
        double result = vacationPayService.calculateVacationPay(100000, 28, null, null);
        assertEquals(100000 / 29.3 * 28, result, 0.01);
    }

    @Test
    public void testCalculateVacationPayWithDatesAndHolidays() {
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        LocalDate endDate = LocalDate.of(2024, 5, 10);

        double result = vacationPayService.calculateVacationPay(100000, null, startDate, endDate);
        assertEquals(100000 / 29.3 * 6, result, 0.01);
    }

    @Test
    public void testCalculateVacationPayWithDatesButVacationDaysProvided() {
        LocalDate startDate = LocalDate.of(2024, 7, 1);
        LocalDate endDate = LocalDate.of(2024, 7, 14);

        double result = vacationPayService.calculateVacationPay(100000, 20, startDate, endDate);
        assertEquals(100000 / 29.3 * 10, result, 0.01);
    }

    @Test
    public void testCalculateVacationPayWithoutDatesOrVacationDays() {
        assertThrows(IllegalArgumentException.class, () -> {
            vacationPayService.calculateVacationPay(100000, null, null, null);
        });
    }
}



