		package dz.airalgerie.conge.entities;
		
		import jakarta.persistence.CascadeType;
		import jakarta.persistence.Column;
		import jakarta.persistence.Entity;
		import jakarta.persistence.GeneratedValue;
		import jakarta.persistence.GenerationType;
		import jakarta.persistence.Id;
		import jakarta.persistence.JoinColumn;
		import jakarta.persistence.ManyToOne;
		import jakarta.persistence.OneToMany;
		import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
		import java.util.List;
		
		import com.fasterxml.jackson.annotation.JsonProperty;
		
		@Entity
		@Table(name = "employe", schema = "dbo")
		
		@Data
		@Setter
		@Getter
		@AllArgsConstructor
		@NoArgsConstructor
		@Builder
		public class Employe {
		    
		   

			@Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    @Column(name = "matricule")
		    private Integer matricule;  // Assuming it's a String, change to appropriate type if needed
		     
		    @Column(name = "date_dentree")
		    @JsonProperty("dateEntree")
		    private LocalDate dateEntree;
		    
		    @Column(name = "name")
		    @JsonProperty("name")
		    private String name;
		    
		    @Column(name = "lastname")
		    @JsonProperty("lastname")
		    private String lastname;
		    
		   
		    @Column(name = "email")
		    @JsonProperty("email")
		    private String email;
		    
		    @Column(name = "motdepasse")
		    @JsonProperty("motdepasse")
		    private String motdepasse;
		    
		    // Relationships
		    @ManyToOne
		    @JoinColumn(name = "role_id")
		    @JsonProperty("role")
		    private Role role;
		    
		    @ManyToOne
		    @JoinColumn(name = "id_affectation")
		    private Affectation affectationEntity;
		    
		    @ManyToOne
		    @JoinColumn(name = "id_fonction")
		    private Fonction fonctionEntity;

		    

		    public void setName(String name) { this.name = name; }
		    public void setLastname(String lastname) { this.lastname = lastname; }
		    public void setDateEntree(LocalDate dateEntree) { this.dateEntree = dateEntree; }
		    public void setEmail(String email) { this.email = email; }
		    public void setMotdepasse(String motdepasse) { this.motdepasse = motdepasse; }
		    public void setRole(Role role) { this.role = role; }
		    public void setAffectationEntity(Affectation affectationEntity) { this.affectationEntity = affectationEntity; }
		    public void setFonctionEntity(Fonction fonctionEntity) { this.fonctionEntity = fonctionEntity; }

		    public Integer getMatricule() {
		        return matricule;
		    }

		    public LocalDate getDateEntree() {
		        return dateEntree;
		    }

		    public String getName() {
		        return name;
		    }

		    public String getLastname() {
		        return lastname;
		    }

		    public String getEmail() {
		        return email;
		    }

		    public String getMotdepasse() {
		        return motdepasse;
		    }

		    public Role getRole() {
		        return role;
		    }

		    public Affectation getAffectationEntity() {
		        return affectationEntity;
		    }

		    public Fonction getFonctionEntity() {
		        return fonctionEntity;
		    }

		}