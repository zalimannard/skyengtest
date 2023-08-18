package ru.kolesnikovdmitry.skyengtest.schema.movement;

import org.springframework.validation.annotation.Validated;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementRequestDto;

@Validated
public interface MovementService {

    void arrive(MovementRequestDto mailItemMovementDto);

    void depart(MovementRequestDto mailItemMovementDto);

    MailItemHistoryResponseDto history(Integer mailItemId);

}
