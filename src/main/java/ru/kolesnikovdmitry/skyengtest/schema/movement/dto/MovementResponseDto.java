package ru.kolesnikovdmitry.skyengtest.schema.movement.dto;

import lombok.Builder;
import lombok.Value;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.MailItem;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOffice;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class MovementResponseDto {

    private Integer id;

    private MailItem mailItem;

    private PostOffice postOffice;

    private LocalDateTime arrivalDateTime;

    private LocalDateTime departureDateTime;

}
