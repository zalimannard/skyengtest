package ru.kolesnikovdmitry.skyengtest.schema.postoffice;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PostOfficeService {

    PostOffice readEntity(Integer postOfficeId);

}
