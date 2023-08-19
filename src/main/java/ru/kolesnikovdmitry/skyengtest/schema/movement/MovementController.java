package ru.kolesnikovdmitry.skyengtest.schema.movement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Передвижения почтовых отправлений")
public class MovementController {

    private final MovementService movementService;

    @PostMapping("${application.endpoints.movement.arrive}")
    @Operation(summary = "Прибытие почтового отправления в промежуточное отделение")
    public MovementResponseDto arrive(@RequestBody @Valid ArriveRequestDto arriveRequestDto) {
        return movementService.arrive(arriveRequestDto);
    }

    @PostMapping("${application.endpoints.movement.depart}")
    @Operation(summary = "Убытие почтового отправления из промежуточного отделения")
    public MovementResponseDto depart(@RequestBody @Valid DepartRequestDto departRequestDto) {
        return movementService.depart(departRequestDto);
    }

}
