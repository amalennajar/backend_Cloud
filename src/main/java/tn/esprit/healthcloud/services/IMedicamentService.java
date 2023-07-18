package tn.esprit.healthcloud.services;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Medicament;

import java.util.List;

public interface IMedicamentService {
    Medicament addMedicament (Medicament m);
    Medicament updateMedicament(Medicament medicament, int idmedicament);
    void deleteMedicament(@PathVariable int id);
    List<Medicament> getAllMedicament();
    Medicament getMedicament(int id);
}
