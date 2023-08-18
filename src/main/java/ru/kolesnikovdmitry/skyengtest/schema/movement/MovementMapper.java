package ru.kolesnikovdmitry.skyengtest.schema.movement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovementMapper {

    public MovementResponseDto toDto(Movement movement) {
        return MovementResponseDto.builder()
                .id(movement.getId())
                .mailItemId(movement.getMailItem().getId())
                .postOfficeId(movement.getPostOffice().getId())
                .arrivalDateTime(movement.getArrivalDateTime())
                .departureDateTime(movement.getDepartureDateTime())
                .build();
    }

    public List<MovementResponseDto> toListDto(List<Movement> movements) {
        return movements.stream().map(this::toDto).toList();
    }

}
