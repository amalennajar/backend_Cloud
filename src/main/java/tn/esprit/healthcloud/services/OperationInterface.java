package tn.esprit.healthcloud.services;



import tn.esprit.healthcloud.entities.Logistique;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.entities.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OperationInterface {
    Operation addOperation(Operation operation);

    void deleteOperation(int idOp);

    List<Operation> getAllOperations();

    List<Operation> searchOperationByType(String typeOp);

    Operation updateOperation(Operation operation);

    List<Logistique> getLogistiquesByOperation(int idOp);
    Operation getOperationById(int idOp);
    List<Operation> findByTypeOp(String typeOp);
    ResponseEntity<Operation> addOperationWithLogistiques(Operation operation) throws Exception;
    Map<String, Long> getTypeOpStatistics();

    public Map<String, Double> getSuccessRatesByType();
    double calculateSuccessRateByType(String typeOp);

    public List<User> getAllUsers();
}

