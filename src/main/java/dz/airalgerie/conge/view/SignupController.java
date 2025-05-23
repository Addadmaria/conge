package dz.airalgerie.conge.view;

import dz.airalgerie.conge.entities.Affectation;
import dz.airalgerie.conge.entities.Fonction;
import dz.airalgerie.conge.entities.Role;
import dz.airalgerie.conge.repositories.AffectationRepository;
import dz.airalgerie.conge.repositories.FonctionRepository;
import dz.airalgerie.conge.repositories.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SignupController {

    private final RoleRepository         roleRepo;
    private final FonctionRepository     fonctionRepo;
    private final AffectationRepository  affectationRepo;

    public SignupController(RoleRepository roleRepo,
                            FonctionRepository fonctionRepo,
                            AffectationRepository affectationRepo) {
        this.roleRepo        = roleRepo;
        this.fonctionRepo    = fonctionRepo;
        this.affectationRepo = affectationRepo;
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        List<Role>        roles        = roleRepo.findAll();
        List<Fonction>    fonctions    = fonctionRepo.findAll();
        List<Affectation> affectations = affectationRepo.findAll();

        model.addAttribute("roles",        roles);
        model.addAttribute("fonctions",    fonctions);
        model.addAttribute("affectations", affectations);
        return "signup";
    }
}
