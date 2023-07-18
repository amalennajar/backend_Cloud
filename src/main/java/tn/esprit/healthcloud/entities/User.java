package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    private String username;
    private String email;

    private String password;
    private String nom;
    private String prenom;
    @Column(name = "resettoken")
    private String resetPasswordToken;



    private String confirmPasswordUser ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> role = new HashSet<>();
    private Boolean statut;
    @Temporal(TemporalType.DATE)
    private Date dateDebutStage;
    @Temporal(TemporalType.DATE)
    private Date dateFinStage;
    private String specialite;
    @Enumerated(EnumType.STRING)
    private Job job;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<DayOff> dayOffs;


    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Cours> coursesAsDoctor;

    @JsonIgnore
    @ManyToMany(mappedBy = "stagiaires")
    private Set<Cours> coursesAsStagiaires;

    @JsonIgnore
    @OneToMany(mappedBy = "stagiaire")
    private Set<Stage> stages;

    public User(String username, String email, String encode) {
    }

}
