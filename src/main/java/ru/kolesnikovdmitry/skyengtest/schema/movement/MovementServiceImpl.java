package ru.kolesnikovdmitry.skyengtest.schema.movement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolesnikovdmitry.skyengtest.exceptions.ConflictException;
import ru.kolesnikovdmitry.skyengtest.exceptions.NotFoundException;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.MailItem;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.MailItemService;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.ArriveRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.DepartRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOffice;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOfficeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementMapper mapper;
    private final MovementRepository repository;

    private final MailItemService mailItemService;
    private final PostOfficeService postOfficeService;

    @Override
    public MovementResponseDto arrive(ArriveRequestDto arriveRequestDto) {
        MailItem mailItem = mailItemService.readEntity(arriveRequestDto.getMailItemId());
        PostOffice postOffice = postOfficeService.readEntity(arriveRequestDto.getPostOfficeId());

        if (isArrived(mailItem)) {
            throw new ConflictException();
        }
        Movement movement = Movement.builder()
                .mailItem(mailItem)
                .postOffice(postOffice)
                .arrivalDateTime(arriveRequestDto.getDateTime())
                .build();

        Movement createdMovement = repository.save(movement);
        return mapper.toDto(createdMovement);
    }

    @Override
    public MovementResponseDto depart(DepartRequestDto departRequestDto) {
        Movement existMovement = repository.findById(departRequestDto.getMovementId())
                .orElseThrow(NotFoundException::new);

        if (existMovement.getDepartureDateTime() != null) {
            throw new ConflictException();
        }
        Movement movement = existMovement.toBuilder()
                .departureDateTime(departRequestDto.getDateTime())
                .build();

        Movement updatedMovement = repository.save(movement);
        return mapper.toDto(updatedMovement);
    }

    private boolean isArrived(MailItem mailItem) {
        List<Movement> movements = repository.findAllByMailItem(mailItem);
        for (Movement movement : movements) {
            if (movement.getDepartureDateTime() == null) {
                return true;
            }
        }
        return false;
    }

}
