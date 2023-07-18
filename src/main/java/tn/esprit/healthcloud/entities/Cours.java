package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cours implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String course_name;
    Date date;
    Duration duration;
    String description;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToMany
    @JoinTable(name = "cours_stagiaires",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaires_id"))
    List<User> stagiaires;
}

