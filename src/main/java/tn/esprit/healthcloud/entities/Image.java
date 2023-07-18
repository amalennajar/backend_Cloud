package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Image {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
	private String path;

	@OneToOne(mappedBy = "image", cascade = {CascadeType.ALL})
    @JsonIgnore
    private Patient patient;
    
  

}
