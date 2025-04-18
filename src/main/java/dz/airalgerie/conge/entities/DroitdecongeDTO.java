package dz.airalgerie.conge.entities;

public class DroitdecongeDTO {
    private Long id;
    private int nbrJourConsommes;
    private int nbrJoursRestants;
    private Integer matricule;
    private Integer idExercice;

    public DroitdecongeDTO() {
    }

    public DroitdecongeDTO(Long id, int nbrJourConsommes, int nbrJoursRestants, Integer matricule, Integer idExercice) {
        this.id = id;
        this.nbrJourConsommes = nbrJourConsommes;
        this.nbrJoursRestants = nbrJoursRestants;
        this.matricule = matricule;
        this.idExercice = idExercice;
    }

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

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Integer idExercice) {
        this.idExercice = idExercice;
    }
}
