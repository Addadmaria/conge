package dz.airalgerie.conge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "exercice")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idexercice")
    private Integer idExercice;

    @Column(name = "label", nullable = false)
    private LocalDate label;

   
    
}