package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.type.MailItemType;

@Value
@Builder(toBuilder = true)
@Schema(description = "DTO для возвращаемых почтовых отправлений")
public class MailItemResponseDto {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "Тип")
    private MailItemType type;

    @Schema(description = "Почтовый индекс получателя")
    private String recipientIndex;

    @Schema(description = "Адрес получателя")
    private String recipientAddress;

    @Schema(description = "Имя получателя")
    private String recipientName;

}
