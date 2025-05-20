package dz.airalgerie.conge.Dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DmdCongeDTO {
    private Date datededamande;
    private int duree;
    private Integer matricule;
    private Integer type_conge;
    private String status;

    public DmdCongeDTO(Date datededamande, int duree) {
        this.datededamande = datededamande;
        this.duree = duree;
		this.status = "PENDING";
        //this.matricule = matricule;
        //this.type_conge = typeconge;
    }
	
	public Date getDatededamande() {
		return datededamande;
	}

	public void setDatededamande(Date datededamande) {
		this.datededamande = datededamande;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Integer getMatricule() {
		return matricule;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public Integer getType_conge() {
		return type_conge;
	}

	public void setType_conge(Integer type_conge) {
		this.type_conge = type_conge;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


