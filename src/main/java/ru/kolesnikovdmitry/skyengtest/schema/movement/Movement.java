package ru.kolesnikovdmitry.skyengtest.schema.movement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kolesnikovdmitry.skyengtest.schema.mailitem.MailItem;
import ru.kolesnikovdmitry.skyengtest.schema.postoffice.PostOffice;

import java.time.LocalDateTime;

@Entity
@Table(name = "movement")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mail_item_id")
    private MailItem mailItem;

    @ManyToOne
    @JoinColumn(name = "post_office_id")
    private PostOffice postOffice;

    private LocalDateTime arrivalDateTime;

    private LocalDateTime departureDateTime;

}
