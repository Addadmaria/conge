// src/main/java/dz/airalgerie/conge/view/TitreCongeController.java
package dz.airalgerie.conge.view;

import dz.airalgerie.conge.entities.TitreConge;
import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.TitreCongeRepository;
import dz.airalgerie.conge.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TitreCongeControllerView {

    @Autowired
    private TitreCongeRepository titreRepo;

    @Autowired
    private UserRepository userRepo;

    public TitreCongeControllerView(TitreCongeRepository t, UserRepository u){
        this.titreRepo=t; this.userRepo=u;
    }

    @GetMapping("/titres")
    public String afficherTitres(@AuthenticationPrincipal UserDetails principal,
                                 Model model) {
        User me = userRepo.findByEmail(principal.getUsername()).orElseThrow();
        List<TitreConge> titres = titreRepo.findByMatricule(me);
        model.addAttribute("titres", titres);
        return "titres";
    }
}
