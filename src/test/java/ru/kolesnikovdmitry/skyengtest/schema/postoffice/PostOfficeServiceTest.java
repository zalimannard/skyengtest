package ru.kolesnikovdmitry.skyengtest.schema.postoffice;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kolesnikovdmitry.skyengtest.exceptions.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostOfficeServiceTest {

    @Autowired
    private PostOfficeService postOfficeService;
    @Autowired
    private PostOfficeRepository postOfficeRepository;

    @BeforeEach
    void setUp() {
        assertThat(postOfficeService).isNotNull();
        assertThat(postOfficeRepository).isNotNull();
    }

    @Test
    void readEntity_existedEntity_ok() {
        PostOffice willExistPostOffice = PostOffice.builder()
                .name("Jones-Kub")
                .address("Suite 205 9647 Nicolas Fall, Port Wilmer, CT 52381")
                .build();
        PostOffice existedPostOffice = postOfficeRepository.save(willExistPostOffice);

        PostOffice readValue = postOfficeService.readEntity(existedPostOffice.getId());

        assertThat(readValue).isEqualTo(existedPostOffice);
    }

    @Test
    void readEntity_nonexistentEntity_notFound() {
        assertThrows(NotFoundException.class, () -> {
            postOfficeService.readEntity(999999);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, 0})
    void readEntity_invalidId_badRequest(Integer id) {
        assertThrows(ConstraintViolationException.class, () -> {
            postOfficeService.readEntity(id);
        });
    }

}