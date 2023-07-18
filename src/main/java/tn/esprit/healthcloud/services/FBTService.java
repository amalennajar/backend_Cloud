package tn.esprit.healthcloud.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.FireBaseToken;
import tn.esprit.healthcloud.repositories.FBTRepository;

@Service
@AllArgsConstructor
public class FBTService {
    FBTRepository fbtRepository;
    public FireBaseToken saveFBT(FireBaseToken FBT){
        return fbtRepository.save(FBT);
    }
    public String getDeviceToken(long userId)
    {
        return fbtRepository.findByUserId(userId).getDeviceToken();
    }

}
