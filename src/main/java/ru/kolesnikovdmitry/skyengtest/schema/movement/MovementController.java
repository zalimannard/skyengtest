package ru.kolesnikovdmitry.skyengtest.schema.movement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.ArriveRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.DepartRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;

@RestController
@RequestMapping("${application.endpoints.api}${application.endpoints.movement.self}")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping("${application.endpoints.movement.arrive}")
    public MovementResponseDto arrive(@RequestBody @Valid ArriveRequestDto arriveRequestDto) {
        return movementService.arrive(arriveRequestDto);
    }

    @PostMapping("${application.endpoints.movement.depart}")
    public MovementResponseDto depart(@RequestBody @Valid DepartRequestDto departRequestDto) {
        return movementService.depart(departRequestDto);
    }

}
