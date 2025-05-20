package dz.airalgerie.conge.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import dz.airalgerie.conge.Dtos.DmdCongeDTO;
import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.Droitdeconge;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.entities.TypeDeConge;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.DroitDeCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;
import dz.airalgerie.conge.repositories.TypeDeCongeRepository;
import dz.airalgerie.conge.services.DmdCongeService;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/dmdconge")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class DmdCongeController {

    @Autowired
    private DmdCongeService dmdCongeService;
    
    @Autowired
    private UserRepository employeRepository;
    @Autowired
    private DmdCongeRepository dmdCongeRepository;
    @Autowired
    private TypeDeCongeRepository typeDeCongeRepository;
    @Autowired
    private DroitDeCongeRepository droitRepo;
   
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DmdCongeDTO dto) {
        Optional<User>           ou    = employeRepository.findById(dto.getMatricule());
        Optional<TypeDeConge>    ot    = typeDeCongeRepository.findById(dto.getType_conge());
        Optional<Droitdeconge>   od    = ou.flatMap(u ->
            droitRepo.findByMatricule(u).stream().findFirst()
        );
        if (ou.isEmpty() || ot.isEmpty() || od.isEmpty()) {
            return ResponseEntity.badRequest().body("Requête invalide");
        }
        User           user   = ou.get();
        TypeDeConge    type   = ot.get();
        Droitdeconge   droit  = od.get();
        if (droit.getNbrJoursRestants() < dto.getDuree()) {
            return ResponseEntity.badRequest()
                .body("Jours restants insuffisants (" + droit.getNbrJoursRestants() + ")");
        }
        droit.setNbrJoursRestants(droit.getNbrJoursRestants() - dto.getDuree());
        droitRepo.save(droit);
        DmdConge conge = new DmdConge();
        conge.setDateDeDemande(dto.getDatededamande());
        conge.setDuree(dto.getDuree());
        conge.setMatricule(user);
        conge.setTypeConge(type);
        dmdCongeRepository.save(conge);
        return ResponseEntity.ok(conge.getId());
    }

    @GetMapping("/all")
    public ResponseEntity<List<DmdCongeDTO>> getAllDmdConges() {
        List<DmdCongeDTO> dtos = dmdCongeService.getAllDemandes().stream()
            .map(d -> new DmdCongeDTO(
                d.getDateDeDemande(),                    // date de la demande
                d.getDuree() //matricule
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}