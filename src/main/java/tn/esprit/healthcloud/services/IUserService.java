package tn.esprit.healthcloud.services;


import tn.esprit.healthcloud.entities.User;

import java.util.List;

public interface IUserService {
    User ajouter (User user);
    User modifier (User user, long id);
    List<User> afficher ();
    User afficherById(long id);
    void supprimer (long id);
    void block (long id);
    void deblock (long id);
    void updatePassword(String email, String newPassword,String confirmPassword);
    void forgotpass(String emailuser);
    public void addwithmail(String emailuser);
    public List<String> getMailsbyRoles();
    public List<User> getUsersbyRoles(int r);
}
