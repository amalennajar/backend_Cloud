package tn.esprit.healthcloud.controllers;

import tn.esprit.healthcloud.entities.BanqueSang;
import tn.esprit.healthcloud.entities.Donateur;
import tn.esprit.healthcloud.repositories.BanqueSangRepository;
import tn.esprit.healthcloud.repositories.DonateurRepository;
import tn.esprit.healthcloud.services.IbanqueSangService;
import tn.esprit.healthcloud.services.IdonateurService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/api/donateurs")
public class DonateurController {


    private IdonateurService donateurService;

    private BanqueSangRepository banqueSangRepository;

    private DonateurRepository donateurRepository;

    private IbanqueSangService banqueSangService;


    @PostMapping("/banques/{idBanque}/donateurs")
    public ResponseEntity<Donateur> addDonateur(@PathVariable int idBanque, @RequestBody Donateur donateur) throws ChangeSetPersister.NotFoundException {
        BanqueSang banqueSang = banqueSangService.getBanqueSangById(idBanque).orElse(null);
        donateur.setBanqueSang(banqueSang);
        banqueSang.getDonateurs().add(donateur);
        banqueSangService.updateBanqueSang(banqueSang);
        Donateur newDonateur = donateurService.addDonateur(idBanque, donateur);
        return ResponseEntity.ok(newDonateur);
    }
    @GetMapping
    public ResponseEntity<List<Donateur>> getAllDonateurs() {
        List<Donateur> donateurs = donateurService.getAllDonateurs();
        if (donateurs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(donateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donateur> getDonateurById(@PathVariable("id") int id) {
        Optional<Donateur> donateur = donateurService.getDonateurById(id);
        return donateur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Donateur> updateDonateur(@PathVariable("id") int id, @RequestBody Donateur donateur) {
        Optional<Donateur> donateurData = donateurService.getDonateurById(id);
        if (donateurData.isPresent()) {
            Donateur updatedDonateur = donateurData.get();
            updatedDonateur.setNomDonateur(donateur.getNomDonateur());
            updatedDonateur.setPrenomDonateur(donateur.getPrenomDonateur());
            updatedDonateur.setAdresse(donateur.getAdresse());
            updatedDonateur.setNumeroTelephone(donateur.getNumeroTelephone());
            updatedDonateur.setTypeSanguin(donateur.getTypeSanguin());
            updatedDonateur.setQuantiteTotale(donateur.getQuantiteTotale());
            updatedDonateur.setDernierDon(donateur.getDernierDon());
            updatedDonateur.setBanqueSang(donateur.getBanqueSang());
            return new ResponseEntity<>(donateurService.updateDonateur(updatedDonateur), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDonateur(@PathVariable("id") int id) {
        try {
            donateurService.deleteDonateurById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
