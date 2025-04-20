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
		import lombok.*;
		
		import java.time.LocalDate;
		import java.util.List;
		
		import com.fasterxml.jackson.annotation.JsonProperty;
		
		@Entity
		@Table(name = "employe", schema = "dbo")
		
		@Data
		@Builder
		@NoArgsConstructor
		@AllArgsConstructor
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

			public Employe(LocalDate dateEntree,String name, String lastname,  String email, String motdepasse,
					Role role, Affectation affectationEntity, Fonction fonctionEntity) {
				this.name = name;
				this.lastname = lastname;
				this.dateEntree = dateEntree;
				this.email = email;
				this.motdepasse = motdepasse;
				this.role = role;
				this.affectationEntity = affectationEntity;
				this.fonctionEntity = fonctionEntity;
			}

			public Integer getMatricule() {
				return matricule;
			}

			public void setMatricule(Integer matricule) {
				this.matricule = matricule;
			}

			public LocalDate getDateEntree() {
				return dateEntree;
			}

			public void setDateEntree(LocalDate dateEntree) {
				this.dateEntree = dateEntree;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getLastname() {
				return lastname;
			}

			public void setLastname(String lastname) {
				this.lastname = lastname;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getMotdepasse() {
				return motdepasse;
			}

			public void setMotdepasse(String motdepasse) {
				this.motdepasse = motdepasse;
			}

			public Role getRole() {
				return role;
			}

			public void setRole(Role role) {
				this.role = role;
			}

			public Affectation getAffectationEntity() {
				return affectationEntity;
			}

			public void setAffectationEntity(Affectation affectationEntity) {
				this.affectationEntity = affectationEntity;
			}

			public Fonction getFonctionEntity() {
				return fonctionEntity;
			}

			public void setFonctionEntity(Fonction fonctionEntity) {
				this.fonctionEntity = fonctionEntity;
			}
		    
		    
		}