package dz.airalgerie.conge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "droitdeconge", schema = "dbo")
public class Droitdeconge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


    @Column(name = "nbrjourconsommes")
    @JsonProperty("nbrJourConsommes")
    private int nbrJourConsommes;  // Nombre de jours consommés

    @Column(name = "nbrjoursrestants")
    @JsonProperty("nbrJoursRestants")
    private int nbrJoursRestants;  // Nouvelle colonne ajoutée

    @ManyToOne
    @JoinColumn(name = "matricule", nullable = false)
    @JsonProperty("matricule")
    private User matricule;
    
    @ManyToOne
    @JoinColumn(name = "idexercice", nullable = false)
    @JsonProperty("idexercice")
    private Exercice idexercice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNbrJourConsommes() {
		return nbrJourConsommes;
	}

	public void setNbrJourConsommes(int nbrJourConsommes) {
		this.nbrJourConsommes = nbrJourConsommes;
	}

	public int getNbrJoursRestants() {
		return nbrJoursRestants;
	}

	public void setNbrJoursRestants(int nbrJoursRestants) {
		this.nbrJoursRestants = nbrJoursRestants;
	}

	public User getMatricule() {
		return matricule;
	}

	public void setMatricule(User matricule) {
		this.matricule = matricule;
	}

	public Exercice getIdexercice() {
		return idexercice;
	}

	public void setIdexercice(Exercice idexercice) {
		this.idexercice = idexercice;
	}

	public Droitdeconge(int nbrJourConsommes, int nbrJoursRestants, User matricule, Exercice idexercice) {
		super();
		this.nbrJourConsommes = nbrJourConsommes;
		this.nbrJoursRestants = nbrJoursRestants;
		this.matricule = matricule;
		this.idexercice = idexercice;
	}

	

    
    
}