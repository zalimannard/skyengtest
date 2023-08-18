package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;

@Service
@RequiredArgsConstructor
public class MailItemServiceImpl implements MailItemService {

    private final MailItemMapper mapper;
    private final MailItemRepository repository;

    @Override
    public MailItemResponseDto register(MailItemRegisterRequestDto mailItemDto) {
        MailItem mailItem = mapper.toEntity(mailItemDto);
        MailItem createdMailItem = registerEntity(mailItem);
        return mapper.toDto(createdMailItem);
    }

    @Override
    public MailItem registerEntity(MailItem mailItem) {
        return repository.save(mailItem);
    }

}
