package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class MovementRequestDto {

    @NotBlank
    @Positive
    private Integer mailItemId;

    @NotBlank
    @Positive
    private Integer postOfficeId;

    @NotNull
    private LocalDateTime dateTime;

}
