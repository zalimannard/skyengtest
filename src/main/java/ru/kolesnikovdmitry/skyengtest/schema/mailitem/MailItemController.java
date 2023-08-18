package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@RestController
@RequestMapping("${application.endpoints.api}/mailitem")
@RequiredArgsConstructor
public class MailItemController {

    private final MailItemService mailItemService;

    @PostMapping("${application.endpoints.mail-item.register}")
    @ResponseStatus(HttpStatus.CREATED)
    public MailItemResponseDto register(@RequestBody @Valid MailItemRegisterRequestDto mailItemDto) {
        return mailItemService.register(mailItemDto);
    }

}
