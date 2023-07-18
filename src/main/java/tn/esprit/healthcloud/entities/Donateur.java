package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Donateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idDonateur")
    private int idDonateur;

    @JsonProperty("nomDonateur")
    private String nomDonateur;

    @JsonProperty("prenomDonateur")
    private String prenomDonateur;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("numeroTelephone")
    private String numeroTelephone;

    @Enumerated(EnumType.STRING)
    @JsonProperty("typeS")
    private TypeSanguin typeSanguin;



    @JsonProperty("quantiteTotale")
    private int quantiteTotale;

    @Temporal(TemporalType.DATE)
    @JsonProperty("dernierDon")
    private Date dernierDon;

    @ManyToOne
    @JoinColumn(name = "idBanque")
    @JsonBackReference
    @JsonProperty("banque")
    private BanqueSang banqueSang;

}
