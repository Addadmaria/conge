package dz.airalgerie.conge.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.Droitdeconge;
import dz.airalgerie.conge.entities.Exercice;
import dz.airalgerie.conge.entities.TitreConge;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.DroitDeCongeRepository;
import dz.airalgerie.conge.repositories.ExerciceRepository;
import dz.airalgerie.conge.repositories.TitreCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;

@Controller
public class HomeController {

    @Autowired
    DmdCongeRepository dmdCongeRepository;

    @Autowired
    DroitDeCongeRepository droitdeconge;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ExerciceRepository exerciceRepo;

    @Autowired
    TitreCongeRepository titreRepo;

    // Accueil page (requires authentication)
    @GetMapping("/acceuil")
    public String accueilPage() {
        return "acceuil";  // maps to templates/acceuil.html
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/errorAcces")
    public String errorAcces() {
        return "acces";
    }

    @GetMapping("/droits")
    public String afficherDroits(@AuthenticationPrincipal UserDetails principal,
                                 Model model) {
        List<Droitdeconge> droits    = droitdeconge.findAll();
        List<User>          users    = userRepo.findAll();
        List<Exercice>      exercices= exerciceRepo.findAll();
        model.addAttribute("droits",    droits);
        model.addAttribute("users",     users);
        model.addAttribute("exercices", exercices);
        return "droits";
    }


    @GetMapping("/demandes/en-cours")
    public String afficherDemandesEnCours(Model model) {
        List<DmdConge> demandes = dmdCongeRepository.findByStatus("en cours");
        
        // Log the retrieved demandes
        System.out.println("Demandes en cours: " + demandes);

        model.addAttribute("demandes", demandes);
        return "demandes-en-cours"; // Return the Thymeleaf template name
    }

    @PostMapping("/api/conge/{id}/approuver")
    public String approuver(@PathVariable Integer id,
                            RedirectAttributes attrs) {
        DmdConge d = dmdCongeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Demande introuvable: " + id));
        d.setStatus("approuvé");
        dmdCongeRepository.save(d);

        attrs.addFlashAttribute("message",
            "La demande n°" + id + " a été approuvée.");

        return "redirect:/demandes/en-cours";
    }

    @GetMapping("/demandes/approuver")
    public String afficherDemandesApprouver(Model model) {
        List<DmdConge> demandes = dmdCongeRepository.findByStatus("approuvé");
        
        // Log the retrieved demandes
        System.out.println("Demandes approuvé: " + demandes);

        model.addAttribute("demandes", demandes);
        return "demandes-approuver";
    }

    @PostMapping("/api/conge/{id}/valider")
    public String valider(@PathVariable Long id,
                          RedirectAttributes attrs) {

        DmdConge d = dmdCongeRepository.findByIdCustom(id)
            .orElseThrow(() -> new IllegalArgumentException("Demande introuvable: " + id));
        d.setStatus("validé");
        dmdCongeRepository.save(d);

        TitreConge t = new TitreConge();
        t.setMatricule(d.getMatricule());
        t.setStatus("validé");
        t.setFilePath("/titres/" + d.getId() + ".pdf");
        t.setDateDeDemande(d.getDateDeDemande());
        titreRepo.save(t);

        attrs.addFlashAttribute("message",
            "Demande n°" + id + " validée et titre créé.");

        return "redirect:/demandes/approuver";
    }


    @GetMapping("/demandes/all")
    public String afficherDemandes(Model model) {
        List<DmdConge> demandes = dmdCongeRepository.findAll();
        
        // Log the retrieved demandes
        System.out.println("Demandes approuvé: " + demandes);

        model.addAttribute("demandes", demandes);
        return "demandes-all"; // Return the Thymeleaf template name
    }

}
