package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
    @Enumerated(EnumType.STRING)
    DayOffStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
