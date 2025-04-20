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
    private Employe matricule;


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


	public Employe getMatricule() {
		return matricule;
	}


	public void setMatricule(Employe matricule) {
		this.matricule = matricule;
	}


	public Exercice getExercice() {
		return exercice;
	}


	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}


	public Droitdeconge(int nbrJourConsommes, int nbrJoursRestants, Employe matricule, Exercice exercice) {
		super();
		this.nbrJourConsommes = nbrJourConsommes;
		this.nbrJoursRestants = nbrJoursRestants;
		this.matricule = matricule;
		this.exercice = exercice;
	}


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idexercice")  // Supposant que c'est une clé étrangère vers Exercice
    private Exercice exercice;


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


	public Employe getMatricule() {
		return matricule;
	}


	public void setMatricule(Employe matricule) {
		this.matricule = matricule;
	}


	public Exercice getExercice() {
		return exercice;
	}


	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}


	public Droitdeconge(int nbrJourConsommes, int nbrJoursRestants, Employe matricule, Exercice exercice) {
		this.nbrJourConsommes = nbrJourConsommes;
		this.nbrJoursRestants = nbrJoursRestants;
		this.matricule = matricule;
		this.exercice = exercice;
	}
    
    
}