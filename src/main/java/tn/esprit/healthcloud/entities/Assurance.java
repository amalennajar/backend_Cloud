package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assurance {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private float montantAssurance;

        private String nomAgence;

        private String adresseAgence;

        @OneToMany(mappedBy = "assurance")
        @JsonBackReference
        private List<Consultation> consultations;

    }
