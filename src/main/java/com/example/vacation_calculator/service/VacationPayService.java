package com.example.vacation_calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VacationPayService {

    private static final double AVG_DAYS_IN_MONTH = 29.3;

    private final HolidayService holidayService;

    public double calculateVacationPay(double averageSalary, Integer vacationDays, LocalDate startDate, LocalDate endDate) {
        double dailySalary = averageSalary / AVG_DAYS_IN_MONTH;

        if (startDate != null && endDate != null) {
            vacationDays = countValidVacationDays(startDate, endDate);
        } else if (vacationDays == null) {
            throw new IllegalArgumentException("Vacation days or vacation period must be provided.");
        }

        return dailySalary * vacationDays;
    }

    private int countValidVacationDays(LocalDate startDate, LocalDate endDate) {
        int validDays = 0;
        Set<LocalDate> holidays = holidayService.getHolidays();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (!isHolidayOrWeekend(date, holidays)) {
                validDays++;
            }
        }

        return validDays;
    }

    private boolean isHolidayOrWeekend(LocalDate date, Set<LocalDate> holidays) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || holidays.contains(date);
    }
}



