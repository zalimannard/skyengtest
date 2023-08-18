package ru.kolesnikovdmitry.skyengtest.schema.movement;

import org.springframework.validation.annotation.Validated;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.ArriveRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.DepartRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;

@Validated
public interface MovementService {

    MovementResponseDto arrive(ArriveRequestDto arriveRequestDto);

    MovementResponseDto depart(DepartRequestDto departRequestDto);

}
