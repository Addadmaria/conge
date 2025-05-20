package dz.airalgerie.conge.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "titreconge", schema = "dbo")
public class TitreConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filepath", nullable = false)
    private String filePath;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "datededemande")
    private Date dateDeDemande;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matricule", nullable = false)
    private User matricule;

    public TitreConge() {}

    public TitreConge(Long id, String filePath, String status, Date dateDeDemande, User matricule) {
        this.id = id;
        this.filePath = filePath;
        this.status = status;
        this.dateDeDemande = dateDeDemande;
        this.matricule = matricule;
    }

    public TitreConge(String filePath, String status, Date dateDeDemande, User matricule) {
        this.filePath = filePath;
        this.status = status;
        this.dateDeDemande = dateDeDemande;
        this.matricule = matricule;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDateDeDemande() { return dateDeDemande; }
    public void setDateDeDemande(Date dateDeDemande) { this.dateDeDemande = dateDeDemande; }

    public User getMatricule() { return matricule; }
    public void setMatricule(User matricule) { this.matricule = matricule; }
}
