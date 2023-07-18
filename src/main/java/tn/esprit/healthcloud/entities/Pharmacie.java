package tn.esprit.healthcloud.entities;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Pharmacie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    private int SellsCount;
    private float SellsTotal;


}
