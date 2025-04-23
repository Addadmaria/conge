package dz.airalgerie.conge.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "exercice")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idexercice")
    private Integer idExercice;

    @Column(name = "label", nullable = false)
    private String label;

	public Integer getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(Integer idExercice) {
		this.idExercice = idExercice;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Exercice() {
		super();
		this.label = label;
	}

	

   
    
}