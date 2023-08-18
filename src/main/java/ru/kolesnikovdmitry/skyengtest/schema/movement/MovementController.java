package ru.kolesnikovdmitry.skyengtest.schema.movement;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementRequestDto;

@RestController
@RequestMapping("${application.endpoints.api}${application.endpoints.movement.self}")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping("${application.endpoints.movement.arrive}")
    public void arrive(@RequestBody @Valid MovementRequestDto mailItemMovementDto) {
        movementService.arrive(mailItemMovementDto);
    }

    @PostMapping("${application.endpoints.movement.depart}")
    public void depart(@RequestBody @Valid MovementRequestDto mailItemMovementDto) {
        movementService.depart(mailItemMovementDto);
    }

    @GetMapping("${application.endpoints.movement.history}/{mailItemId}")
    public MailItemHistoryResponseDto history(@PathVariable @Positive Integer mailItemId) {
        return movementService.history(mailItemId);
    }

}
