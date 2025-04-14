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
    @JoinColumn(name = "matricule")
    private Employe matricule;  // Stocke directement le matricule (si ce n'est pas une clé étrangère)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idexercice")  // Supposant que c'est une clé étrangère vers Exercice
    private Exercice exercice;
}