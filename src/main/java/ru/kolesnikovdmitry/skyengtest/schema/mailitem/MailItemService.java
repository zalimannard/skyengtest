package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import org.springframework.validation.annotation.Validated;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@Validated
public interface MailItemService {

    MailItem readEntity(Integer id);

    MailItemResponseDto register(MailItemRegisterRequestDto mailItemDto);

    MailItem registerEntity(MailItem mailItem);

}
