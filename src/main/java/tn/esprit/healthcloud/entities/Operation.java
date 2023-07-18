// Operation.java

package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@JsonIgnoreProperties("logistiques")
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOp;
    private boolean success;
    private String nomP;
    private String nomChi;
    private String emailP;
    @Temporal(TemporalType.DATE)
    private Date dateOp;

    private String typeOp;

    private int idChambre;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
    @JoinTable(
            name = "operation_logistique",
            joinColumns = @JoinColumn(name = "idOp"),
            inverseJoinColumns = @JoinColumn(name = "idLogi")
    )
    @JsonProperty("logistiques")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLogi")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties("operations")
    private Set<Logistique> logistiques = new HashSet<>();

    public void addLogistique(Logistique logistique) {
        this.logistiques.add(logistique);
        logistique.getOperations().add(this);
    }

    public void removeLogistique(Logistique logistique) {
        this.logistiques.remove(logistique);
        logistique.getOperations().remove(this);
    }

    // getters and setters
}