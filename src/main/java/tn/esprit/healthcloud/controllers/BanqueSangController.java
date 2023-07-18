package tn.esprit.healthcloud.controllers;

import tn.esprit.healthcloud.entities.BanqueSang;
import tn.esprit.healthcloud.services.IbanqueSangService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/api/banquesang")
public class BanqueSangController {

    private IbanqueSangService banqueSangService;

    @GetMapping
    public ResponseEntity<List<BanqueSang>> getAllBanqueSangs() {
        List<BanqueSang> banqueSangs = banqueSangService.getAllBanqueSangs();
        return new ResponseEntity<>(banqueSangs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BanqueSang> getBanqueSangById(@PathVariable int id) {
        Optional<BanqueSang> banqueSang = banqueSangService.getBanqueSangById(id);
        if (banqueSang.isPresent()) {
            return new ResponseEntity<>(banqueSang.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BanqueSang> addBanqueSang(@RequestBody BanqueSang banqueSang) {
        BanqueSang newBanqueSang = banqueSangService.addBanqueSang(banqueSang);
        return new ResponseEntity<>(newBanqueSang, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BanqueSang> updateBanqueSang(@PathVariable int id, @RequestBody BanqueSang banqueSang) {
        Optional<BanqueSang> existingBanqueSang = banqueSangService.getBanqueSangById(id);
        if (existingBanqueSang.isPresent()) {
            banqueSang.setIdBanqueSang(id);
            BanqueSang updatedBanqueSang = banqueSangService.updateBanqueSang(banqueSang);
            return new ResponseEntity<>(updatedBanqueSang, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBanqueSangById(@PathVariable int id) {
        Optional<BanqueSang> banqueSang = banqueSangService.getBanqueSangById(id);
        if (banqueSang.isPresent()) {
            banqueSangService.deleteBanqueSangById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{idBanqueSang}/retirerSang")
    public ResponseEntity<String> retirerSang(@PathVariable int idBanqueSang, @RequestBody Map<String, Object> requestBody) {
        int quantiteSangRetiree = Integer.parseInt(requestBody.get("quantiteSangRetiree").toString());
        try {
            banqueSangService.retirerSang(idBanqueSang, quantiteSangRetiree);
            return ResponseEntity.ok().body("La quantité de sang a été retirée avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }}
