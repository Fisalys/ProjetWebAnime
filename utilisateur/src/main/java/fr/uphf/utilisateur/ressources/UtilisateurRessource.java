package fr.uphf.utilisateur.ressources;

import fr.uphf.utilisateur.dto.UtilisateurDTO;
import fr.uphf.utilisateur.exeptions.ProcessExeption;
import fr.uphf.utilisateur.services.CommonService;
import fr.uphf.utilisateur.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("utilisateur")
public class UtilisateurRessource extends CommonService {

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping ("create")
    public UtilisateurDTO createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) throws ProcessExeption {
        utilisateurService.validateUtilisateurModel(utilisateurDTO);
        return utilisateurService.saveUtilisateur(utilisateurDTO);
    }

    @GetMapping("users")
    public UtilisateurDTO getUtilisateurByUsername(@RequestParam String username) throws ProcessExeption
    {
        return utilisateurService.getUtilisateurByUsername(username);
    }
}
