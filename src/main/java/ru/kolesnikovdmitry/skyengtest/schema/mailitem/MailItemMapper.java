package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import org.springframework.stereotype.Component;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.type.MailItemType;

@Component
public class MailItemMapper {

    public MailItem toEntity(MailItemRegisterRequestDto mailItemRegisterRequestDto) {
        return MailItem.builder()
                .type(MailItemType.valueOf(mailItemRegisterRequestDto.getType()))
                .recipientIndex(mailItemRegisterRequestDto.getRecipientIndex())
                .recipientAddress(mailItemRegisterRequestDto.getRecipientAddress())
                .recipientName(mailItemRegisterRequestDto.getRecipientName())
                .status(MailItemStatus.valueOf(mailItemRegisterRequestDto.getStatus()))
                .build();
    }

    public MailItemResponseDto toDto(MailItem mailItem) {
        return MailItemResponseDto.builder()
                .id(mailItem.getId())
                .type(String.valueOf(mailItem.getType()))
                .recipientIndex(mailItem.getRecipientIndex())
                .recipientAddress(mailItem.getRecipientAddress())
                .recipientName(mailItem.getRecipientName())
                .status(String.valueOf(mailItem.getStatus()))
                .build();
    }

}
