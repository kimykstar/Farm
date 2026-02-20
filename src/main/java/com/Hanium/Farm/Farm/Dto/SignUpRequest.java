package com.Hanium.Farm.Farm.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
        @NotBlank String id,
        @NotBlank String pw,
        @NotBlank String name,
        @NotBlank String phone,
        @Min(value=1) @Max(value=200) @NotNull int age) {}
