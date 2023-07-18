package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.BanqueSang;
import tn.esprit.healthcloud.entities.Donateur;
import tn.esprit.healthcloud.repositories.DonateurRepository;
import tn.esprit.healthcloud.repositories.BanqueSangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonateurService implements IdonateurService {

    @Autowired
    private DonateurRepository donateurRepository;
    @Autowired
    private BanqueSangRepository banqueSangRepository;
@Autowired
private BanqueSangService banqueSangService;
    @Override
    public List<Donateur> getAllDonateurs() {
        return donateurRepository.findAll();
    }

    @Override
    public Optional<Donateur> getDonateurById(int id) {
        return donateurRepository.findById(id);
    }

    @Override
    public Donateur addDonateur(int idBanque, Donateur donateur) {
        BanqueSang banqueSang = banqueSangRepository.findById(idBanque).orElse(null);
        donateur.setBanqueSang(banqueSang);
        donateur.setTypeSanguin(banqueSang.getTypeSanguin()); // Set the typeSanguin field of the Donateur object
        banqueSang.getDonateurs().add(donateur);
        banqueSang.setQuantiteTotale(banqueSang.getQuantiteTotale() + donateur.getQuantiteTotale());
        banqueSangRepository.save(banqueSang);
        return donateurRepository.save(donateur);
    }


    @Override
    public Donateur updateDonateur(Donateur donateur) {
        return donateurRepository.save(donateur);
    }

    @Override
    public void deleteDonateurById(int id) {
        donateurRepository.deleteById(id);
    }
}
