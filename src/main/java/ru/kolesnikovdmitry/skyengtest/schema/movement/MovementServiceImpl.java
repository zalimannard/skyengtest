package ru.kolesnikovdmitry.skyengtest.schema.movement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementRequestDto;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    @Override
    public void arrive(MovementRequestDto mailItemMovementDto) {

    }

    @Override
    public void depart(MovementRequestDto mailItemMovementDto) {

    }

    @Override
    public MailItemHistoryResponseDto history(Integer mailItemId) {
        return null;
    }

}
