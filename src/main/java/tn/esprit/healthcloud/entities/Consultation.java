package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultation")
    private Long id_consultation;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "consultation_time")
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_rdv")
    private StatusRDV statusRDV;

    @Column(name = "motif_consultation")
    private String motifConsultation;

    @ElementCollection
    private List<String> antecedentsMedicaux= new ArrayList<String>();

    @Column(name = "suivi")
    private String suivi;
    /*
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;
     */
/*
    @ManyToOne
    private Medecin medecin; */

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name="facture_id", nullable=false)
    private Facture facture;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="assurance_id", nullable=false)
    private Assurance assurance;




   /*
   @OneToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    */
    private String comment;
    public String getComment() {
        return comment;
    }
}
