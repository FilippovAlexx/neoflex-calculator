package com.example.vacation_calculator.controller;

import com.example.vacation_calculator.request.VacationPayRequest;
import com.example.vacation_calculator.response.VacationPayResponse;
import com.example.vacation_calculator.service.VacationPayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @GetMapping("/calculate")
    @Operation(summary = "Calculate vacation pay")
    public ResponseEntity<VacationPayResponse> calculate(@Valid @ModelAttribute VacationPayRequest request) {
        try {
            double vacationPay = vacationPayService.calculateVacationPay(
                    request.getAverageSalary(),
                    request.getVacationDays(),
                    request.getStartDate(),
                    request.getEndDate()
            );
            return ResponseEntity.ok(new VacationPayResponse(vacationPay));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid request");
        }

        return ResponseEntity.badRequest().build();

    }
}
