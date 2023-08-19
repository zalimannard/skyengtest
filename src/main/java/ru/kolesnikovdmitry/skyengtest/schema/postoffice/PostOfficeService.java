package ru.kolesnikovdmitry.skyengtest.schema.postoffice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public interface PostOfficeService {

    PostOffice readEntity(@NotNull @Positive Integer postOfficeId);

}
