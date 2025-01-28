package de.learnapp.Gamer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamerService {
    @Autowired
    private GamerRepository gamerRepository;
    public GamerService(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }
    public GamerModel registerGamer(String login, String password, String email){
        if(login == null && password == null){
            return null;
        }else{
            if(gamerRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Doppelte Anmeldung");
            }
            GamerModel gamerModel = new GamerModel();
            gamerModel.setLogin(login);
            gamerModel.setPassword(password);
            gamerModel.setEmail(email);
            return gamerRepository.save(gamerModel);
        }
    }
    public GamerModel authenticate (String login, String password){
        return gamerRepository.findByLoginAndPassword(login,password).orElse(null);
    }
}
