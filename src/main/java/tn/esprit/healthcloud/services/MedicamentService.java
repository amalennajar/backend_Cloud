package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Medicament;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.MedicamentRepository;
import tn.esprit.healthcloud.repositories.UserRepository;

import javax.mail.MessagingException;
import java.util.List;
@Service
@EnableScheduling
public class MedicamentService implements IMedicamentService{
    @Autowired
    MedicamentRepository medicamentRepository;
    @Autowired
    UserService userService;
    @Autowired
    EmailSampleService emailSampleService;
    @Override
    public Medicament addMedicament(Medicament m)
    {
        Medicament medicament =medicamentRepository.save(m);
        return medicament;
    }
    @Override
    public Medicament updateMedicament(Medicament medicament , int idmedicament) {
        Medicament med = medicamentRepository.findById(idmedicament).get();
        med=medicament;
        medicamentRepository.save(med);
        return medicament;
    }

    @Override
    public void deleteMedicament(@PathVariable int id) {
        medicamentRepository.deleteById(id);
    }


    @Override
    public List<Medicament> getAllMedicament() {
        return (List<Medicament>) medicamentRepository.findAll();
    }


    @Override
    public Medicament getMedicament(int id) {
        Medicament medicament = medicamentRepository.findById(id).orElse(null);
        return medicament;
    }
    @Scheduled(cron = "* * 8 * * *") // Run every day at 8:00 AM
    public void checkStockLevels() throws MessagingException {
        List<Medicament> lowStockMedicaments = medicamentRepository.findByStockLessThan(10); // Find all medications with stock level less than 10
        List<String> pharmacienEmails = userService.getMailsbyRoles();
        for (String email : pharmacienEmails) {
            for (Medicament medicament : lowStockMedicaments) {
                String message = String.format("Stock level of medication %s is running low. Current stock level: %d", medicament.getNom(), medicament.getStock());
                System.out.println(email);
                emailSampleService.sendEmailSample(email, message, message);
            }
        }
    }



}


