package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@RestController
@RequestMapping("${application.endpoints.api}${application.endpoints.mailitem.self}")
@RequiredArgsConstructor
public class MailItemController {

    private final MailItemService mailItemService;

    @PostMapping("${application.endpoints.mailitem.register}")
    @ResponseStatus(HttpStatus.CREATED)
    public MailItemResponseDto register(@RequestBody MailItemRegisterRequestDto mailItemDto) {
        return mailItemService.register(mailItemDto);
    }

    @PostMapping("${application.endpoints.mailitem.deliver}/{itemId}")
    public MailItemResponseDto deliver(@PathVariable Integer itemId) {
        return mailItemService.deliver(itemId);
    }

}
