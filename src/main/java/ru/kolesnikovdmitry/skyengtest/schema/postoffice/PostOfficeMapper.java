package ru.kolesnikovdmitry.skyengtest.schema.postoffice;

import org.springframework.stereotype.Component;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.dto.PostOfficeResponseDto;

@Component
public class PostOfficeMapper {

    public PostOfficeResponseDto toDto(PostOffice postOffice) {
        return PostOfficeResponseDto.builder()
                .id(postOffice.getId())
                .address(postOffice.getAddress())
                .name(postOffice.getName())
                .build();
    }

}