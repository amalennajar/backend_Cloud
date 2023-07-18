package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DossierMedical implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idD;
    LocalDate dateCreation;
    String typeMaladie;
    String traitement;
    String maladiesChroniques;
    String testsMedicaux;
    LocalDate derniereMaj;
    


    //dossierM-Patient
    @OneToOne(mappedBy = "dossierMedical", cascade = {CascadeType.ALL})
    @JsonIgnore
    private Patient patient;
    
   
}

