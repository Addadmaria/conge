// src/main/java/dz/airalgerie/conge/view/MesDemandesController.java
package dz.airalgerie.conge.view;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.TypeDeCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MesDemandesController {

    private final DmdCongeRepository dmdCongeRepo;
    private final UserRepository userRepo;
    private final TypeDeCongeRepository typeDeCongeRepo;

    public MesDemandesController(DmdCongeRepository dmdCongeRepo,
                                 UserRepository userRepo,
                                 TypeDeCongeRepository typeDeCongeRepo) {
        this.dmdCongeRepo     = dmdCongeRepo;
        this.userRepo         = userRepo;
        this.typeDeCongeRepo  = typeDeCongeRepo;
    }

    @GetMapping("/mes-demandes")
    public String mesDemandesEnCours(
            @AuthenticationPrincipal UserDetails principal,
            Model model) {

        // 1) Find our User entity by principal username (email)
        User me = userRepo.findByEmail(principal.getUsername())
                          .orElseThrow(() -> new IllegalArgumentException(
                              "Utilisateur non trouvé: " + principal.getUsername()));

        // 2) Fetch only my demandes
        List<DmdConge> mesDemandes = dmdCongeRepo.findByMatricule(me);

        // 3) Add to model for table
        model.addAttribute("demandes", mesDemandes);

        // 4) Add current user's matricule for hidden field in modal
        model.addAttribute("currentMatricule", me.getMatricule());

        // 5) Add available leave types for the select
        model.addAttribute("typesDeConge", typeDeCongeRepo.findAll());

        return "mes-demandes";  // resolves to templates/mes-demandes.html
    }
}
