package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.Logistique;
import tn.esprit.healthcloud.entities.Operation;
import tn.esprit.healthcloud.exceptions.ResourceNotFoundException;
import tn.esprit.healthcloud.repositories.LogistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class LogistiqueService implements ILogistiqueService , Serializable {

    private final LogistiqueRepository logistiqueRepository;

    @Autowired
    public LogistiqueService(LogistiqueRepository logistiqueRepository) {
        this.logistiqueRepository = logistiqueRepository;
    }

    @Override
    public Logistique addLogistique(Logistique logistique) {
        return logistiqueRepository.save(logistique);
    }

    @Override
    public void deleteLogistique(int idLogi) {
        logistiqueRepository.deleteById(idLogi);
    }

    @Override
    public List<Logistique> getAllLogistiques() {
        return logistiqueRepository.findAll();
    }

    @Override
    public List<Logistique> searchLogistiqueByType(String typeLogi) {
        return logistiqueRepository.findByTypeLogiContainingIgnoreCase(typeLogi);
    }

    @Override
    public Logistique updateLogistique(Logistique logistique) {
        return logistiqueRepository.save(logistique);
    }

    @Override
    public List<Logistique> getLogistiquesByOperation(Operation operation) {
        return logistiqueRepository.findByOperations(operation);
    }

    @Override
    public Logistique getLogistiqueById(int idLogi) {
        Optional<Logistique> optionalLogistique = logistiqueRepository.findById(idLogi);
        if (optionalLogistique.isPresent()) {
            return optionalLogistique.get();
        } else {
            throw new ResourceNotFoundException("Logistique", "id", idLogi);
        }
    }


    public Logistique saveLogistique(Logistique logistique) {
        return logistiqueRepository.save(logistique);
    }
}
