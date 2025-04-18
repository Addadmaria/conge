package dz.airalgerie.conge.Dtos;

import java.time.LocalDate;

public class EmployeDTO {
    private Integer matricule;
    private String name;
    private String lastname;
    private LocalDate dateEntree;
    private String email;
    private String motdepasse;
    private Integer roleId;
    private Integer idAffectation;
    private Integer idFonction;

    public EmployeDTO() {
    }

    public EmployeDTO(Integer matricule, String name, String lastname, LocalDate dateEntree,
                      String email, String motdepasse, Integer roleId, Integer idAffectation, Integer idFonction) {
        this.matricule = matricule;
        this.name = name;
        this.lastname = lastname;
        this.dateEntree = dateEntree;
        this.email = email;
        this.motdepasse = motdepasse;
        this.roleId = roleId;
        this.idAffectation = idAffectation;
        this.idFonction = idFonction;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Integer idAffectation) {
        this.idAffectation = idAffectation;
    }

    public Integer getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Integer idFonction) {
        this.idFonction = idFonction;
    }
}