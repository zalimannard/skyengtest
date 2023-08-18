package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class MailItemRequestDto {

    @NotBlank
    private String type;

    @NotBlank
    private String recipientIndex;

    @NotBlank
    private String recipientAddress;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s\\-()]+$")
    private String recipientName;

    @NotNull
    @Positive
    private Integer acceptingOfficeId;

    @NotNull
    private LocalDateTime acceptanceDateTime;

}
