package com.Hanium.Farm.Farm.Dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String id, @NotBlank String pw) {
}
