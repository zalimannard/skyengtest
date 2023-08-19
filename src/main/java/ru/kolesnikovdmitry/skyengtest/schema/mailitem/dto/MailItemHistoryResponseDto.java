package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;

import java.util.List;

@Value
@Builder(toBuilder = true)
@Schema(description = "DTO для просмотра статуса и полной истории движения")
public class MailItemHistoryResponseDto {

    @Schema(description = "id")
    private Integer mailItemId;

    @Schema(description = "Статус")
    private MailItemStatus status;

    @Schema(description = "История передвижений, отсортированная по датам отправления")
    private List<MovementResponseDto> movementHistory;

}
