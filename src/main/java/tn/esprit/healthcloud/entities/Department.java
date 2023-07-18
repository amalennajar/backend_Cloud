package tn.esprit.healthcloud.entities;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String depName;
    String depDesc;

}
