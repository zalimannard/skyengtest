package ru.kolesnikovdmitry.skyengtest.schema.movement;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kolesnikovdmitry.skyengtest.exceptions.BadRequestException;
import ru.kolesnikovdmitry.skyengtest.exceptions.ConflictException;
import ru.kolesnikovdmitry.skyengtest.exceptions.NotFoundException;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.MailItemService;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.ArriveRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.DepartRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOffice;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOfficeRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovementServiceTest {

    @Autowired
    private MovementService movementService;
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    @Autowired
    private MailItemService mailItemService;

    private PostOffice postOffice1;
    private PostOffice postOffice2;

    @BeforeEach
    void setUp() {
        assertThat(movementService).isNotNull();
        assertThat(movementRepository).isNotNull();
        assertThat(postOfficeRepository).isNotNull();
        assertThat(mailItemService).isNotNull();

        PostOffice postOfficeToCreate = PostOffice.builder()
                .name("Littel, Kihn and Stoltenberg")
                .address("3913 Will Lakes, South Doriantown, WV 52372")
                .build();
        postOffice1 = postOfficeRepository.save(postOfficeToCreate);
        assertThat(postOffice1).isNotNull();
        postOfficeToCreate = PostOffice.builder()
                .name("Stamm-Murray")
                .address("5191 Sheilah Branch, North Roosevelt, MI 20836")
                .build();
        postOffice2 = postOfficeRepository.save(postOfficeToCreate);
        assertThat(postOffice2).isNotNull();
    }

    @Test
    void arrive_allRight_ok() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        MovementResponseDto movementDto = movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(mailItem.getId())
                .postOfficeId(postOffice1.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        assertThat(movementDto.getArrivalDateTime()).isNotNull();
    }

    @Test
    void arrive_nonexistentMailItem_notFound() {
        assertThrows(NotFoundException.class, () -> {
            movementService.arrive(ArriveRequestDto.builder()
                    .mailItemId(999999)
                    .postOfficeId(postOffice1.getId())
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void arrive_invalidMailItemId_badRequest(Integer id) {
        assertThrows(ConstraintViolationException.class, () -> {
            movementService.arrive(ArriveRequestDto.builder()
                    .mailItemId(id)
                    .postOfficeId(postOffice1.getId())
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @Test
    void arrive_nonexistentPostOffice_notFound() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        assertThrows(NotFoundException.class, () -> {
            movementService.arrive(ArriveRequestDto.builder()
                    .mailItemId(mailItem.getId())
                    .postOfficeId(999999)
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void arrive_invalidPostOfficeId_exception(Integer id) {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        assertThrows(Exception.class, () -> {
            movementService.arrive(ArriveRequestDto.builder()
                    .mailItemId(mailItem.getId())
                    .postOfficeId(id)
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @Test
    void arrive_timeIsNotSpecified_badRequest() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        assertThrows(BadRequestException.class, () -> {
            movementService.arrive(ArriveRequestDto.builder()
                    .mailItemId(mailItem.getId())
                    .postOfficeId(postOffice1.getId())
                    .dateTime(null)
                    .build()
            );
        });
    }

    @Test
    void arrive_alreadyInOffice_conflict() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(mailItem.getId())
                .postOfficeId(postOffice1.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        assertThrows(ConflictException.class, () -> {
            movementService.arrive(ArriveRequestDto.builder()
                    .mailItemId(mailItem.getId())
                    .postOfficeId(postOffice2.getId())
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @Test
    void depart_allRight_ok() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        MovementResponseDto movementDto = movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(mailItem.getId())
                .postOfficeId(postOffice1.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        movementDto = movementService.depart(DepartRequestDto.builder()
                .movementId(movementDto.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        assertThat(movementDto.getDepartureDateTime()).isNotNull();
    }

    @Test
    void depart_nonexistentMovement_notFound() {
        assertThrows(NotFoundException.class, () -> {
            movementService.depart(DepartRequestDto.builder()
                    .movementId(999999)
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void depart_invalidMovementId_badRequest(Integer id) {
        assertThrows(ConstraintViolationException.class, () -> {
            movementService.depart(DepartRequestDto.builder()
                    .movementId(id)
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

    @Test
    void depart_timeIsNotSpecified_badRequest() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        MovementResponseDto movementDto = movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(mailItem.getId())
                .postOfficeId(postOffice1.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        assertThrows(BadRequestException.class, () -> {
            movementService.depart(DepartRequestDto.builder()
                    .movementId(movementDto.getId())
                    .dateTime(null)
                    .build()
            );
        });
    }

    @Test
    void depart_alreadyDeparted_conflict() {
        MailItemRegisterRequestDto mailItemDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto mailItem = mailItemService.register(mailItemDto);

        MovementResponseDto movementDto = movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(mailItem.getId())
                .postOfficeId(postOffice1.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        movementService.depart(DepartRequestDto.builder()
                .movementId(movementDto.getId())
                .dateTime(LocalDateTime.now())
                .build()
        );

        assertThrows(ConflictException.class, () -> {
            movementService.depart(DepartRequestDto.builder()
                    .movementId(movementDto.getId())
                    .dateTime(LocalDateTime.now())
                    .build()
            );
        });
    }

}