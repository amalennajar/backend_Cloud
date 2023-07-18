package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Logistique;
import tn.esprit.healthcloud.entities.Operation;

import java.util.List;

public interface ILogistiqueService {
    Logistique addLogistique(Logistique logistique);
    void deleteLogistique(int idLogi);
    List<Logistique> getAllLogistiques();
    List<Logistique> searchLogistiqueByType(String typeLogi);
    Logistique updateLogistique(Logistique logistique);
    List<Logistique> getLogistiquesByOperation(Operation operation);
    Logistique getLogistiqueById(int idLogi);

}
