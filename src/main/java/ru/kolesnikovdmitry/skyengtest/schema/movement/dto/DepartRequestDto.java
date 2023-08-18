package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class DepartRequestDto {

    @NotNull
    @Positive
    private Integer movementId;

    @NotNull
    private LocalDateTime dateTime;

}
