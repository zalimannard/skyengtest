package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@Validated
public interface MailItemService {

    MailItem readEntity(@NotNull @Positive Integer id);

    MailItemResponseDto register(@NotNull @Valid MailItemRegisterRequestDto mailItemDto);

    MailItem registerEntity(@NotNull MailItem mailItem);

    MailItemResponseDto deliver(@NotNull @Positive Integer itemId);

    MailItem deliverEntity(@NotNull @Positive Integer itemId);

    MailItemHistoryResponseDto history(@NotNull @Positive Integer itemId);

}
