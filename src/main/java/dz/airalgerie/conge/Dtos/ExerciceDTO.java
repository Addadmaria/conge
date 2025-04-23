package dz.airalgerie.conge.Dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

public class ExerciceDTO {
    private Integer idExercice;
    private String label;
    
    public ExerciceDTO() {
    }

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

	public ExerciceDTO(String label) {
		super();
		this.label = label;
	}
    
    
    
    
    
}