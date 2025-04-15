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
		    
		    @Column(name = "name")
		    @JsonProperty("name")
		    private String name;
		    
		    @Column(name = "lastname")
		    @JsonProperty("lastname")
		    private String lastname;
		    
		    @Column(name = "date_dentree")
		    @JsonProperty("dateEntree")
		    private LocalDate dateEntree;
		    
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
		    
		    @OneToMany(mappedBy = "matricule", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<Droitdeconge> droitdeconge;
		
		    @OneToMany(mappedBy = "matricule", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<DmdConge> dmdConge;
		}