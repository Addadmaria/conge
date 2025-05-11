package dz.airalgerie.conge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

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

    @Column(name = "status", length = 255, columnDefinition = "varchar(255) default 'PENDING'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricule")
    private User matricule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeconge")
    private TypeDeConge typeConge;

    // Default constructor
    public DmdConge() {
        this.status = "PENDING";
    }

    // All-args constructor
    public DmdConge(Long id, Date dateDeDemande, int duree, String status, User matricule, TypeDeConge typeConge) {
        this.id = id;
        this.dateDeDemande = dateDeDemande;
        this.duree = duree;
        this.status = status == null ? "PENDING" : status;
        this.matricule = matricule;
        this.typeConge = typeConge;
    }

    // Convenience constructor
    public DmdConge(Date dateDeDemande, int duree, User matricule, TypeDeConge typeConge, String status) {
        this.dateDeDemande = dateDeDemande;
        this.duree = duree;
        this.matricule = matricule;
        this.typeConge = typeConge;
        this.status = status == null ? "PENDING" : status;
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
        this.status = status;
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
