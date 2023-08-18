package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemMovementRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterResponseDto;

@RestController
@RequestMapping("${application.endpoints.api}${application.endpoints.mail-item.self}")
@RequiredArgsConstructor
public class MailItemController {

    @PostMapping("${application.endpoints.mail-item.register}")
    @ResponseStatus(HttpStatus.CREATED)
    public MailItemRegisterResponseDto register(@RequestBody @Valid MailItemRegisterRequestDto mailItemDto) {
        return null;
    }

    @PostMapping("${application.endpoints.mail-item.arrive}")
    public void arrive(@RequestBody @Valid MailItemMovementRequestDto mailItemMovementDto) {

    }

    @PostMapping("${application.endpoints.mail-item.depart}")
    public void depart(@RequestBody @Valid MailItemMovementRequestDto mailItemMovementDto) {

    }

    @PostMapping("${application.endpoints.mail-item.deliver}/{mailItemId}")
    public void deliver(@PathVariable @Positive Integer mailItemId) {

    }

    @GetMapping("${application.endpoints.mail-item.history}/{mailItemId}")
    public void history(@PathVariable @Positive Integer mailItemId) {

    }

}
