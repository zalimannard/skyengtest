package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
@Schema(description = "DTO для запроса на прибытие в отделение")
public class ArriveRequestDto {

    @NotNull
    @Positive
    @Schema(description = "id почтового отправления")
    private Integer mailItemId;

    @NotNull
    @Positive
    @Schema(description = "id почтового отделения")
    private Integer postOfficeId;

    @NotNull
    @Schema(description = "Время прибытия")
    private LocalDateTime dateTime;

}
