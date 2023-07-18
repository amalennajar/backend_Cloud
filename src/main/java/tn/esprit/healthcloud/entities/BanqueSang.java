package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BanqueSang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBanqueSang;

    @Enumerated(EnumType.STRING)
    private TypeSanguin typeSanguin;

    @OneToMany(mappedBy ="banqueSang")
    @JsonProperty("donateurs")
    @JsonManagedReference
    private Set<Donateur> donateurs;

    @JsonProperty("quantiteTotale")
    private int quantiteTotale;

    @JsonProperty("sangRetire")
    private int sangRetire;


    public void retirerSang(int quantiteSangRetiree) {
        if (quantiteSangRetiree <= 0) {
            throw new IllegalArgumentException("La quantité de sang à retirer doit être supérieure à zéro");
        }

        if (this.quantiteTotale < quantiteSangRetiree) {
            throw new IllegalArgumentException("La quantité de sang à retirer est supérieure à la quantité totale disponible");
        }

        this.quantiteTotale -= quantiteSangRetiree;
        this.sangRetire += quantiteSangRetiree;
    }
    // constructors, getters and setters, toString method
}
