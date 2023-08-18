package ru.kolesnikovdmitry.skyengtest.schema.postoffice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PostOfficeResponseDto {

    private Integer id;

    private String name;

    private String address;

}
