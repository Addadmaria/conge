package dz.airalgerie.conge.entities;

import jakarta.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dmdconge", schema = "dbo")
public class DmdConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "datededamande")
    private Date dateDeDemande;

    @Column(name = "duree")
    private int duree;

    @Column(name = "status", length = 255)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "matricule")
	private User matricule;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typeconge")
	private TypeDeConge typeConge;


    // Default constructor
    public DmdConge() {
        this.status = "en cours";
    }

    // All-args constructor
    public DmdConge(Long id, Date dateDeDemande, int duree, String status, User matricule, TypeDeConge typeConge) {
        this.id = id;
        this.dateDeDemande = dateDeDemande;
        this.duree = duree;
        this.status = resolveStatus(status);
        this.matricule = matricule;
        this.typeConge = typeConge;
    }

    // Convenience constructor
    public DmdConge(Date dateDeDemande, int duree, User matricule, TypeDeConge typeConge, String status) {
        this.dateDeDemande = dateDeDemande;
        this.duree = duree;
        this.status = resolveStatus(status);
        this.matricule = matricule;
        this.typeConge = typeConge;
    }

    // Private helper method to resolve default status
    private String resolveStatus(String status) {
        return status == null ? "en cours" : status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeDemande() {
        return dateDeDemande;
    }

    public void setDateDeDemande(Date dateDeDemande) {
        this.dateDeDemande = dateDeDemande;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = resolveStatus(status);
    }

    public User getMatricule() {
        return matricule;
    }

    public void setMatricule(User matricule) {
        this.matricule = matricule;
    }

    public TypeDeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeDeConge typeConge) {
        this.typeConge = typeConge;
    }
}
