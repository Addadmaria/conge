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
    @Column(name = "id")
    private Long id;

    @Column(name = "datededamande")
    @JsonProperty("datededamande")
    private Date datededamande;

    @Column(name = "duree")
    @JsonProperty("duree")
    private int duree;
    
    @ManyToOne
    @JoinColumn(name = "matricule")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "typeconge")
    private TypeDeConge typeconge;

}
