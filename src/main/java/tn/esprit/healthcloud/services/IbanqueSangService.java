package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.BanqueSang;

import java.util.List;
import java.util.Optional;

public interface IbanqueSangService {

    List<BanqueSang> getAllBanqueSangs();
    Optional<BanqueSang> getBanqueSangById(int id);
    BanqueSang addBanqueSang(BanqueSang banqueSang);
    BanqueSang updateBanqueSang(BanqueSang banqueSang);
    void deleteBanqueSangById(int id);
    void retirerSang(int id, int quantiteSangRetiree);

}
