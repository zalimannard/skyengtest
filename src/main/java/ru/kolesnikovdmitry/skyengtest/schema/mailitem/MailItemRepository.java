package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailItemRepository extends JpaRepository<MailItem, Integer> {

}
