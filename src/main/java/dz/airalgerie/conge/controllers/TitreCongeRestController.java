package dz.airalgerie.conge.controllers;


import dz.airalgerie.conge.entities.TitreConge;
import dz.airalgerie.conge.repositories.TitreCongeRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/titres")
public class TitreCongeRestController {

    private final TitreCongeRepository repo;
    public TitreCongeRestController(TitreCongeRepository repo){this.repo=repo;}

    @GetMapping("/download/{id}")
    public ResponseEntity<FileSystemResource> download(@PathVariable Long id) {
        TitreConge t = repo.findById(id).orElseThrow();
        FileSystemResource fsr = new FileSystemResource(t.getFilePath());
        HttpHeaders h = new HttpHeaders();
        h.setContentDisposition(ContentDisposition
          .attachment()
          .filename(fsr.getFilename())
          .build());
        h.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(fsr,h,HttpStatus.OK);
    }
}
