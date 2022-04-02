package fr.uphf.utilisateur.ressources;

import fr.uphf.utilisateur.dto.UtilisateurDTO;
import fr.uphf.utilisateur.exeptions.ProcessExeption;
import fr.uphf.utilisateur.services.CommonService;
import fr.uphf.utilisateur.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("utilisateur")
public class UtilisateurRessource extends CommonService {

    @Autowired
    UtilisateurService utilisateurService;

    /*
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

     */

    @Autowired
    private RestTemplate restTemplate;

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

    @DeleteMapping
    public void deleteUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) throws ProcessExeption {
        utilisateurService.deleteUtilisateur(utilisateurDTO);
    }
}
