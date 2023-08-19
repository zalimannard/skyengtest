package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@RestController
@RequestMapping("${application.endpoints.api}${application.endpoints.mailitem.self}")
@RequiredArgsConstructor
@Tag(name = "Почтовое отправление")
public class MailItemController {

    private final MailItemService mailItemService;

    @PostMapping("${application.endpoints.mailitem.register}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Регистрация нового почтового отправления")
    public MailItemResponseDto register(@RequestBody MailItemRegisterRequestDto mailItemDto) {
        return mailItemService.register(mailItemDto);
    }

    @PostMapping("${application.endpoints.mailitem.deliver}/{itemId}")
    @Operation(summary = "Получение отправления конечным получателем")
    public MailItemResponseDto deliver(@PathVariable Integer itemId) {
        return mailItemService.deliver(itemId);
    }

    @GetMapping("${application.endpoints.mailitem.history}/{itemId}")
    @Operation(summary = "Просмотр статуса и полной истории движения почтового отправления")
    public MailItemHistoryResponseDto history(@PathVariable Integer itemId) {
        return mailItemService.history(itemId);
    }

}
