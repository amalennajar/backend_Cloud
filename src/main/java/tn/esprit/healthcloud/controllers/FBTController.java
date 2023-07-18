package tn.esprit.healthcloud.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.healthcloud.entities.Cours;
import tn.esprit.healthcloud.entities.FireBaseToken;
import tn.esprit.healthcloud.services.FBTService;

@RestController
@RequestMapping("/firebaseToken")
@AllArgsConstructor

public class FBTController {
    FBTService fbtService;

    @PostMapping()
    public ResponseEntity<FireBaseToken> saveCours(@RequestBody FireBaseToken fbt) {
        FireBaseToken newfbt = fbtService.saveFBT(fbt);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
