package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kolesnikovdmitry.skyengtest.exceptions.ConflictException;
import ru.kolesnikovdmitry.skyengtest.exceptions.NotFoundException;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemHistoryResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemRegisterRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.dto.MailItemResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.type.MailItemType;
import ru.kolesnikovdmitry.skyengtest.schema.movement.MovementService;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.ArriveRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.DepartRequestDto;
import ru.kolesnikovdmitry.skyengtest.schema.movement.dto.MovementResponseDto;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOffice;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOfficeRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MailItemServiceTest {

    @Autowired
    private MailItemService mailItemService;
    @Autowired
    private MailItemRepository mailItemRepository;
    @Autowired
    private PostOfficeRepository postOfficeRepository;
    @Autowired
    private MovementService movementService;

    private PostOffice postOffice;

    @BeforeEach
    void setUp() {
        assertThat(mailItemService).isNotNull();
        assertThat(mailItemRepository).isNotNull();
        assertThat(postOfficeRepository).isNotNull();

        PostOffice postOfficeToCreate = PostOffice.builder()
                .name("SCRM")
                .address("674 Connelly Unions, Freddyfort, CO 83071")
                .build();
        postOffice = postOfficeRepository.save(postOfficeToCreate);
        assertThat(postOffice).isNotNull();
    }

    @Test
    void readEntity_existedEntity_ok() {
        MailItem willExistMailItem = MailItem.builder()
                .type(MailItemType.LETTER)
                .recipientIndex("78222")
                .recipientAddress("Suite 590 766 Joan Green, New Jeanene, KY 71237")
                .recipientName("Munch")
                .status(MailItemStatus.IN_TRANSIT)
                .build();
        MailItem existedMailItem = mailItemRepository.save(willExistMailItem).toBuilder()
                .movements(null)
                .build();

        MailItem readValue = mailItemService.readEntity(existedMailItem.getId()).toBuilder()
                .movements(null)
                .build();

        assertThat(readValue).isEqualTo(existedMailItem);
    }

    @Test
    void readEntity_nonexistentEntity_notFound() {
        assertThrows(NotFoundException.class, () -> {
            mailItemService.readEntity(999999);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void readEntity_invalidId_badRequest(Integer id) {
        assertThrows(ConstraintViolationException.class, () -> {
            mailItemService.readEntity(id);
        });
    }

    @Test
    void register_allRight_ok() {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto actual = mailItemService.register(mailItemRegisterRequestDto);
        MailItemResponseDto expected = MailItemResponseDto.builder()
                .id(actual.getId())
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"LETTER", "PACKAGE", "PARCEL", "POSTCARD"})
    void register_differentTypes_ok(String type) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type(type)
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();
        MailItemResponseDto actual = mailItemService.register(mailItemRegisterRequestDto);
        MailItemResponseDto expected = MailItemResponseDto.builder()
                .id(actual.getId())
                .type(type)
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"IN_TRANSIT", "DELIVERED"})
    void register_differentStatuses_ok(String status) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status(status)
                .build();
        MailItemResponseDto actual = mailItemService.register(mailItemRegisterRequestDto);
        MailItemResponseDto expected = MailItemResponseDto.builder()
                .id(actual.getId())
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status(status)
                .build();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "1", "LETER"})
    void register_invalidType_badRequest(String type) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type(type)
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status("IN_TRANSIT")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            mailItemService.register(mailItemRegisterRequestDto);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void register_invalidRecipientIndex_badRequest(String recipientIndex) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex(recipientIndex)
                .recipientAddress("7716 O'Kon Bypass, Dickimouth, MT 15024")
                .recipientName("Winslow Homer")
                .status("IN_TRANSIT")
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            mailItemService.register(mailItemRegisterRequestDto);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {""})
    void register_invalidRecipientAddress_badRequest(String recipientAddress) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("63486")
                .recipientAddress(recipientAddress)
                .recipientName("Renoir")
                .status("IN_TRANSIT")
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            mailItemService.register(mailItemRegisterRequestDto);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "123"})
    void register_invalidRecipientName_badRequest(String recipientName) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("96369")
                .recipientAddress("Apt. 800 74255 Dickens Stravenue, Lake Zorastad, MA 11226")
                .recipientName(recipientName)
                .status("IN_TRANSIT")
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            mailItemService.register(mailItemRegisterRequestDto);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "1", "IN_TRANSI"})
    void register_invalidStatus_badRequest(String status) {
        MailItemRegisterRequestDto mailItemRegisterRequestDto = MailItemRegisterRequestDto.builder()
                .type("LETTER")
                .recipientIndex("41236")
                .recipientAddress("Suite 806 134 Hugh Shore, Lake Robynshire, MI 91964")
                .recipientName("Escher")
                .status(status)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            mailItemService.register(mailItemRegisterRequestDto);
        });
    }

    @Test
    void deliver_allRight_ok() {
        MailItem willExistMailItem = MailItem.builder()
                .type(MailItemType.LETTER)
                .recipientIndex("19558")
                .recipientAddress("Apt. 137 30865 Pagac Divide, Lake Barb, AR 88248")
                .recipientName("Michelangelo")
                .status(MailItemStatus.IN_TRANSIT)
                .build();
        MailItem existedMailItem = mailItemRepository.save(willExistMailItem);
        MailItemResponseDto actual = mailItemService.deliver(existedMailItem.getId());

        MailItemResponseDto expected = MailItemResponseDto.builder()
                .id(actual.getId())
                .type("LETTER")
                .recipientIndex("19558")
                .recipientAddress("Apt. 137 30865 Pagac Divide, Lake Barb, AR 88248")
                .recipientName("Michelangelo")
                .status("DELIVERED")
                .build();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void deliver_alreadyDelivered_conflict() {
        MailItem willExistMailItem = MailItem.builder()
                .type(MailItemType.LETTER)
                .recipientIndex("80122")
                .recipientAddress("653 Bulah Glen, Port Montyfort, NC 51592")
                .recipientName("Ansel Adams")
                .status(MailItemStatus.DELIVERED)
                .build();
        MailItem existedMailItem = mailItemRepository.save(willExistMailItem);

        assertThrows(ConflictException.class, () -> {
            mailItemService.deliver(existedMailItem.getId());
        });
    }

    @Test
    void deliver_nonexistentMailItemId_notFound() {
        assertThrows(NotFoundException.class, () -> {
            mailItemService.deliver(999999);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void deliver_invalidMailItemId_badRequest(Integer id) {
        assertThrows(ConstraintViolationException.class, () -> {
            mailItemService.deliver(id);
        });
    }

    @Test
    void history_emptyHistory_ok() {
        MailItem willExistMailItem = MailItem.builder()
                .type(MailItemType.LETTER)
                .recipientIndex("37599")
                .recipientAddress("Apt. 815 25030 Del Ports, Nolanstad, MT 35483")
                .recipientName("Vincent")
                .status(MailItemStatus.IN_TRANSIT)
                .build();
        MailItem existedMailItem = mailItemRepository.save(willExistMailItem);

        MailItemHistoryResponseDto mailItemHistoryResponseDto = mailItemService.history(existedMailItem.getId());
        assertThat(mailItemHistoryResponseDto.getMovementHistory().size()).isEqualTo(0);
    }

    @Test
    void history_oneElementInHistory_ok() {
        MailItem willExistMailItem = MailItem.builder()
                .type(MailItemType.LETTER)
                .recipientIndex("37599")
                .recipientAddress("Apt. 815 25030 Del Ports, Nolanstad, MT 35483")
                .recipientName("Vincent")
                .status(MailItemStatus.IN_TRANSIT)
                .build();
        MailItem existedMailItem = mailItemRepository.save(willExistMailItem);

        movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(existedMailItem.getId())
                .postOfficeId(postOffice.getId())
                .dateTime(LocalDateTime.now())
                .build());

        MailItemHistoryResponseDto mailItemHistoryResponseDto = mailItemService.history(existedMailItem.getId());
        assertThat(mailItemHistoryResponseDto.getMovementHistory().size()).isEqualTo(1);
    }

    @Test
    void history_twoElementInHistory_ok() {
        MailItem willExistMailItem = MailItem.builder()
                .type(MailItemType.LETTER)
                .recipientIndex("37599")
                .recipientAddress("Apt. 815 25030 Del Ports, Nolanstad, MT 35483")
                .recipientName("Vincent")
                .status(MailItemStatus.IN_TRANSIT)
                .build();
        MailItem existedMailItem = mailItemRepository.save(willExistMailItem);

        MovementResponseDto movementResponseDto1 = movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(existedMailItem.getId())
                .postOfficeId(postOffice.getId())
                .dateTime(LocalDateTime.now())
                .build());
        movementService.depart(DepartRequestDto.builder()
                .movementId(movementResponseDto1.getId())
                .dateTime(LocalDateTime.now())
                .build());
        movementService.arrive(ArriveRequestDto.builder()
                .mailItemId(existedMailItem.getId())
                .postOfficeId(postOffice.getId())
                .dateTime(LocalDateTime.now())
                .build());

        MailItemHistoryResponseDto mailItemHistoryResponseDto = mailItemService.history(existedMailItem.getId());
        assertThat(mailItemHistoryResponseDto.getMovementHistory().size()).isEqualTo(2);
    }

    @Test
    void history_nonexistentMailItemId_notFound() {
        assertThrows(NotFoundException.class, () -> {
            mailItemService.history(999999);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void history_invalidMailItemId_badRequest(Integer id) {
        assertThrows(ConstraintViolationException.class, () -> {
            mailItemService.history(id);
        });
    }

}