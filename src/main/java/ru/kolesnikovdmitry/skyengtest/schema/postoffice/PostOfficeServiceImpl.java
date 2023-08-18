package ru.kolesnikovdmitry.skyengtest.schema.postoffice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostOfficeServiceImpl implements PostOfficeService {

    private final PostOfficeRepository repository;

    @Override
    public PostOffice readEntity(Integer postOfficeId) {
        return repository.findById(postOfficeId).orElseThrow();
    }

}
