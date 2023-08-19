package ru.kolesnikovdmitry.skyengtest.schema.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.MailItem;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findAllByMailItem(MailItem mailItem);

}
