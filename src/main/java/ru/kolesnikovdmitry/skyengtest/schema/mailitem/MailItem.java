package ru.kolesnikovdmitry.skyengtest.schema.mailitem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.status.MailItemStatus;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.type.MailItemType;
import ru.kolesnikovdmitry.skyengtest.schema.movement.Movement;

import java.util.List;

@Entity
@Table(name = "mail_item")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MailItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private MailItemType type;

    private String recipientIndex;

    private String recipientAddress;

    private String recipientName;

    private MailItemStatus status;

    @OneToMany(mappedBy = "mailItem", fetch = FetchType.EAGER)
    private List<Movement> movements;

}
