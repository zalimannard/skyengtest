package ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class MailItemResponseDto {

    private Integer id;

    private String type;

    private String recipientIndex;

    private String recipientAddress;

    private String recipientName;

    private String status;

}
