package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
@Schema(description = "DTO для запроса на убытие из отделения")
public class DepartRequestDto {

    @NotNull
    @Positive
    @Schema(description = "id объекта перемещения")
    private Integer movementId;

    @NotNull
    @Schema(description = "Время убытия")
    private LocalDateTime dateTime;

}
