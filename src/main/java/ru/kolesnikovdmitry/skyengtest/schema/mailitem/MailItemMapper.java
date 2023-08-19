package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import org.springframework.stereotype.Component;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@Component
public class MailItemMapper {

    public MailItem toEntity(MailItemRegisterRequestDto mailItemRegisterRequestDto) {
        return MailItem.builder()
                .type(mailItemRegisterRequestDto.getType())
                .recipientIndex(mailItemRegisterRequestDto.getRecipientIndex())
                .recipientAddress(mailItemRegisterRequestDto.getRecipientAddress())
                .recipientName(mailItemRegisterRequestDto.getRecipientName())
                .status(mailItemRegisterRequestDto.getStatus())
                .build();
    }

    public MailItemResponseDto toDto(MailItem mailItem) {
        return MailItemResponseDto.builder()
                .id(mailItem.getId())
                .type(mailItem.getType())
                .recipientIndex(mailItem.getRecipientIndex())
                .recipientAddress(mailItem.getRecipientAddress())
                .recipientName(mailItem.getRecipientName())
                .build();
    }

}
