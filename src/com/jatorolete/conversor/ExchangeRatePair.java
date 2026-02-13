package com.jatorolete.conversor;

public record ExchangeRatePair(
        String result,
        String Base_code,
        String target_code,
        double conversion_rate
) {}
