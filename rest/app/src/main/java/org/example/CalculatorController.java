package com.example.rest;

import com.example.calculator.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public BigDecimal add(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.add(a, b);
    }

    @GetMapping("/subtract")
    public BigDecimal subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/multiply")
    public BigDecimal multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    public BigDecimal divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return calculatorService.divide(a, b);
    }
}