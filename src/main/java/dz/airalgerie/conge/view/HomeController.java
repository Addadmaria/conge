package dz.airalgerie.conge.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dz.airalgerie.conge.entities.DmdConge;
import dz.airalgerie.conge.entities.Droitdeconge;
import dz.airalgerie.conge.repositories.DmdCongeRepository;
import dz.airalgerie.conge.repositories.DroitDeCongeRepository;

@Controller
public class HomeController {

    @Autowired
    DmdCongeRepository dmdCongeRepository;

    @Autowired
    DroitDeCongeRepository droitdeconge;

    // Accueil page (requires authentication)
    @GetMapping("/acceuil")
    public String accueilPage() {
        return "acceuil";  // maps to templates/acceuil.html
    }

    @GetMapping("/titres")
    public String titresPage() {
        return "titres";
    }

    // @GetMapping("/demandes")
    // public String demandesPage() {
    //     return "demandes";
    // }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/droits")
    public String afficherDroits(Model model) {
        List<Droitdeconge> droits = droitdeconge.findAll();
        System.out.println("Droits récupérés: " + droits);
        model.addAttribute("droits", droits);
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
    public String valider(@PathVariable Integer id,
                            RedirectAttributes attrs) {
        DmdConge d = dmdCongeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Demande introuvable: " + id));
        d.setStatus("validé");
        dmdCongeRepository.save(d);

        attrs.addFlashAttribute("message",
            "La demande n°" + id + " a été validé.");

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
