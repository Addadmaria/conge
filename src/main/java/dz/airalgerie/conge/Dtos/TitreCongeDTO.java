// src/main/java/dz/airalgerie/conge/Dtos/TitreCongeDTO.java
package dz.airalgerie.conge.Dtos;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TitreCongeDTO {
    private Long id;
    private String filePath;
    private String status;
}
