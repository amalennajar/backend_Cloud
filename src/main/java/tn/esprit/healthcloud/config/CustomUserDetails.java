package tn.esprit.healthcloud.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.healthcloud.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
    private Boolean statut;

    private String firstName;
    private String lastName;


    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String username,String firstName,String lastName, String email, String password, Boolean statut,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.statut = statut;
        this.authorities = authorities;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getIdUser(),
                user.getUsername(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getPassword(),
                user.getStatut(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public Boolean getStatut() {
        return statut;
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstname() {
        return firstName;
    }
    public String getLastname() {
        return lastName;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomUserDetails user = (CustomUserDetails) o;
        return Objects.equals(id, user.id);
    }

}
