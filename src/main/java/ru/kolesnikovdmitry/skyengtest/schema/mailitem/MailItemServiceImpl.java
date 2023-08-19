package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikovdmitry.skyengtest.exceptions.NotFoundException;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;
import ru.kolesnikovdmitry.skyengtest.schema.movement.Movement;
import ru.kolesnikovdmitry.skyengtest.schema.movement.MovementMapper;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailItemServiceImpl implements MailItemService {

    private final MailItemMapper mapper;
    private final MailItemRepository repository;

    private final MovementMapper movementMapper;

    @Override
    public MailItem readEntity(Integer id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
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
                .orElseThrow(NotFoundException::new);
        MailItem mailItem = existMailItem.toBuilder()
                .status(MailItemStatus.DELIVERED)
                .build();
        return repository.save(mailItem);
    }

    @Override
    public MailItemHistoryResponseDto history(Integer itemId) {
        MailItem mailItem = repository.findById(itemId)
                .orElseThrow(NotFoundException::new);
        List<Movement> movements = mailItem.getMovements();
        movements.sort(Comparator.comparing(Movement::getArrivalDateTime));

        return MailItemHistoryResponseDto.builder()
                .mailItemId(mailItem.getId())
                .status(String.valueOf(mailItem.getStatus()))
                .movementHistory(movementMapper.toListDto(movements))
                .build();
    }

}
