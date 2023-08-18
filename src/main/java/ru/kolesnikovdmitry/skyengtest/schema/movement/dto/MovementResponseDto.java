package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class MovementResponseDto {

    private Integer id;

    private Integer mailItemId;

    private Integer postOfficeId;

    private LocalDateTime arrivalDateTime;

    private LocalDateTime departureDateTime;

}
