package tn.esprit.healthcloud.services;

import javassist.NotFoundException;
import tn.esprit.healthcloud.entities.BanqueSang;
import tn.esprit.healthcloud.repositories.BanqueSangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BanqueSangService implements IbanqueSangService {

    @Autowired
    BanqueSangRepository banqueSangRepository;

    @Override
    public List<BanqueSang> getAllBanqueSangs() {
        return banqueSangRepository.findAll();
    }

    @Override
    public Optional<BanqueSang> getBanqueSangById(int id) {
        return banqueSangRepository.findById(id);
    }

    @Override
    public BanqueSang addBanqueSang(BanqueSang banqueSang) {
        return banqueSangRepository.save(banqueSang);
    }

    @Override
    public BanqueSang updateBanqueSang(BanqueSang banqueSang) {
        return banqueSangRepository.save(banqueSang);
    }

    @Override
    public void deleteBanqueSangById(int id) {
        banqueSangRepository.deleteById(id);
    }

    public void retirerSang(int idBanqueSang, int quantiteSangRetiree) {
        Optional<BanqueSang> optionalBanqueSang = banqueSangRepository.findById(idBanqueSang);
        if (optionalBanqueSang.isPresent()) {
            BanqueSang banqueSang = optionalBanqueSang.get();
            int quantiteTotale = banqueSang.getQuantiteTotale();
            int sangRetire = banqueSang.getSangRetire();
            if (quantiteTotale - quantiteSangRetiree >= 0) {
                banqueSang.setSangRetire(sangRetire + quantiteSangRetiree);
                banqueSang.setQuantiteTotale(quantiteTotale - quantiteSangRetiree);
                banqueSangRepository.save(banqueSang);
            } else {
                throw new IllegalArgumentException("La quantité de sang à retirer est supérieure à la quantité disponible");
            }
        } else {
            throw new IllegalArgumentException("La banque de sang avec l'ID " + idBanqueSang + " n'existe pas");
        }
    }
    }
