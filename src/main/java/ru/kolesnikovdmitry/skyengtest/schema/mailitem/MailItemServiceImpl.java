package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;

@Service
@RequiredArgsConstructor
public class MailItemServiceImpl implements MailItemService {

    private final MailItemMapper mapper;
    private final MailItemRepository repository;

    @Override
    public MailItem readEntity(Integer id) {
        return repository.findById(id).orElseThrow();
    }

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

    @Override
    public MailItemResponseDto deliver(Integer itemId) {
        MailItem deliveredMailItem = deliverEntity(itemId);
        return mapper.toDto(deliveredMailItem);
    }

    @Override
    public MailItem deliverEntity(Integer itemId) {
        MailItem existMailItem = repository.findById(itemId)
                .orElseThrow();
        MailItem mailItem = existMailItem.toBuilder()
                .status(MailItemStatus.DELIVERED)
                .build();
        return repository.save(mailItem);
    }

}
