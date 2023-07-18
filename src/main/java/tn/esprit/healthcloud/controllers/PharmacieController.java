package tn.esprit.healthcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import tn.esprit.healthcloud.entities.Pharmacie;
import tn.esprit.healthcloud.repositories.PharmacieRepository;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Pharmacie")
public class PharmacieController {

    @Autowired
    private PharmacieRepository pharmacieRepository;

    @PostMapping("/add-sells-count/{sellsCount}")
    public ResponseEntity<String> setSellsCount(@PathVariable("sellsCount") String sellsCount ){
        int sellsCountValue = Integer.parseInt(sellsCount);
        Pharmacie pharmacie = pharmacieRepository.findById(1).get();
        pharmacie.setSellsCount(pharmacie.getSellsCount() + sellsCountValue);
        pharmacieRepository.save(pharmacie);
        return ResponseEntity.ok("Sells count updated successfully");
    }


    @PostMapping("/add-sells-total/{sellsTotal}")
    public ResponseEntity<String> setSellsTotal(@PathVariable("sellsTotal") String sellsTotal){
        float sellsTotalValue = Float.parseFloat(sellsTotal);
        Pharmacie pharmacie = pharmacieRepository.findById(1).get();
        pharmacie.setSellsTotal(pharmacie.getSellsTotal()+sellsTotalValue);
        pharmacieRepository.save(pharmacie);
        return ResponseEntity.ok("Sells count updated successfully");
    }

    @GetMapping("/get-sells-count")
    public float getSellsCount(){
        Pharmacie pharmacie = pharmacieRepository.findById(1).get();
        return pharmacie.getSellsCount();
    }

    @GetMapping("/get-sells-total")
    public float getSellsTotal(){
        Pharmacie pharmacie = pharmacieRepository.findById(1).get();
        return pharmacie.getSellsTotal();
    }





}
