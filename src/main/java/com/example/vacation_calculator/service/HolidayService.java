package com.example.vacation_calculator.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Service
public class HolidayService {
    private final Set<LocalDate> holidays = new HashSet<>(Arrays.asList(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 6, 12),
            LocalDate.of(2024, 11, 4)
    ));
}