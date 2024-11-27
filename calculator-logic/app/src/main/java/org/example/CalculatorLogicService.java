package com.example.calculator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorLogicService {
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a.divide(b);
    }
}