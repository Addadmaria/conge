package dz.airalgerie.conge.entities;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;



import java.util.Date;

@Entity
@Table(name = "dmdconge", schema = "dbo")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DmdConge {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datededamande")
    private Date datededamande;

    @Column(name = "duree")
    private int duree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricule")
    private Employe matricule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeconge")
    private TypeDeConge typeconge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Employe getMatricule() {
		return matricule;
	}

	public void setMatricule(Employe matricule) {
		this.matricule = matricule;
	}

	public TypeDeConge getTypeconge() {
		return typeconge;
	}

	public void setTypeconge(TypeDeConge typeconge) {
		this.typeconge = typeconge;
	}

	public DmdConge(Date datededamande, int duree, Employe matricule, TypeDeConge typeconge) {
		this.datededamande = datededamande;
		this.duree = duree;
		this.matricule = matricule;
		this.typeconge = typeconge;
	}
    
	
    

}
