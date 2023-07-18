package tn.esprit.healthcloud.config.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String role;
    private Boolean statut;
    private String firstName;
    private String lastName;

    public JwtResponse(String accessToken, Long id, String username, String firstName, String lastName, String email, String role, Boolean statut) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.statut = statut;
        this.role = role;
        this.firstName= firstName;
        this.lastName= lastName;
    }

    public void setStatut(boolean status) {
        this.statut = status;
    }

    public Boolean getStatut() {
        return statut;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }


    public String getFirstName(){return firstName;};
    public String getLastName(){return lastName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
}
