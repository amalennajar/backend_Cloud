package tn.esprit.healthcloud.controllers;

import tn.esprit.healthcloud.entities.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.services.EmailService;
import tn.esprit.healthcloud.services.ILogistiqueService;
import tn.esprit.healthcloud.services.NotificationService;
import tn.esprit.healthcloud.services.OperationInterface;
import tn.esprit.healthcloud.entities.User;

import java.util.*;

@RestController
@RequestMapping("/api/operations")
@AllArgsConstructor
public class OperationController {
    private final NotificationService notificationService;
    private final OperationInterface operationInterface;
    private final ILogistiqueService logistiqueService;
    private final EmailService emailService;
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "/oplogi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> addOperationWithLogistiques(@RequestBody Operation operation) throws Exception {
        return operationInterface.addOperationWithLogistiques(operation);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable int id) {
        Operation operation = operationInterface.getOperationById(id);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable int id) {
        operationInterface.deleteOperation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("")
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationInterface.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("")
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation) {
        Operation updatedOperation = operationInterface.updateOperation(operation);
        return new ResponseEntity<>(updatedOperation, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/operations/type/{type}")
    public ResponseEntity<List<Operation>> findByTypeOp(@PathVariable String type) {
        List<Operation> operations = operationInterface.findByTypeOp(type);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/operations/anytype/{type}")
    public ResponseEntity<List<Operation>> searchOperationByType(@PathVariable String type) {
        List<Operation> operations = operationInterface.searchOperationByType(type);
        if (operations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/typeOp")
    public Map<String, Long> getTypeOpStatistics() {
        return operationInterface.getTypeOpStatistics();
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/success-rates")
    public Map<String, Double> getSuccessRatesByType() {
        return operationInterface.getSuccessRatesByType();
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/users")
    public ResponseEntity<List<User>> updateOperation() {
        return new ResponseEntity<>(operationInterface.getAllUsers(), HttpStatus.OK);
    }

}
