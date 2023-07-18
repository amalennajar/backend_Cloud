// Logistique.java

package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLogi")
public class Logistique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogi;

    private String typeLogi;

    private String nomLogi;

    private int nombreLogi;

    @ManyToMany(mappedBy = "logistiques")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Operation> operations;

    // getters and setters
}
