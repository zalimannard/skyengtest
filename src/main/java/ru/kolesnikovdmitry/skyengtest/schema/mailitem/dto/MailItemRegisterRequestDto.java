package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class MailItemRegisterRequestDto {

    @NotBlank
    private String type;

    @NotBlank
    private String recipientIndex;

    @NotBlank
    private String recipientAddress;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s\\-()]+$")
    private String recipientName;

    @NotBlank
    private String status;

}
