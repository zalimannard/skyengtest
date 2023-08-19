package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
@Schema(description = "Объект перемещения")
public class MovementResponseDto {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "id почтового отправления")
    private Integer mailItemId;

    @Schema(description = "id почтового отделения")
    private Integer postOfficeId;

    @Schema(description = "Время прибытия")
    private LocalDateTime arrivalDateTime;

    @Schema(description = "Время убытия")
    private LocalDateTime departureDateTime;

}
