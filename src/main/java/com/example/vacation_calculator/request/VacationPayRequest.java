package com.example.vacation_calculator.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class VacationPayRequest {
    @NotNull(message = "averageSalary can not be null")
    @Min(value = 0, message = "averageSalary must be positive")
    private double averageSalary;

    @Min(value = 1, message = "vacationDays more than 0")
    private Integer vacationDays;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
}
