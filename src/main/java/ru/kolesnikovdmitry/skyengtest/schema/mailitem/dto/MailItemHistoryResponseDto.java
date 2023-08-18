package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import lombok.Builder;
import lombok.Value;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class MailItemHistoryResponseDto {

    private Integer mailItemId;

    private List<MovementResponseDto> movementHistory;

}
