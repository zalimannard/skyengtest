package ru.kolesnikovdmitry.skyengtest.schema.postoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Schema(description = "Почтовое отделения")
public class PostOfficeResponseDto {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "Название")
    private String name;

    @Schema(description = "Адрес")
    private String address;

}
