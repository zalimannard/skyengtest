package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.type.MailItemType;

@Value
@Builder(toBuilder = true)
@Schema(description = "DTO для регистрации нового отправления")
public class MailItemRegisterRequestDto {

    @NotBlank
    @Schema(description = "Тип")
    private MailItemType type;

    @NotBlank
    @Schema(description = "Почтовый индекс получателя")
    private String recipientIndex;

    @NotBlank
    @Schema(description = "Адрес получателя")
    private String recipientAddress;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s\\-()]+$")
    @Schema(description = "Имя получателя")
    private String recipientName;

    @NotBlank
    @Schema(description = "Статус")
    private MailItemStatus status;

}
