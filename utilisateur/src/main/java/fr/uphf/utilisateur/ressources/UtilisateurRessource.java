package fr.uphf.utilisateur.ressources;

import fr.uphf.utilisateur.dto.UtilisateurDTO;
import fr.uphf.utilisateur.exeptions.ProcessExeption;
import fr.uphf.utilisateur.services.CommonService;
import fr.uphf.utilisateur.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("utilisateur")
public class UtilisateurRessource extends CommonService {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping ("create")
    public UtilisateurDTO createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) throws ProcessExeption {
        utilisateurService.validateUtilisateurModel(utilisateurDTO);
        return utilisateurService.saveUtilisateur(utilisateurDTO);
    }

    @GetMapping("user")
    public UtilisateurDTO getUtilisateurByUsername(@RequestParam String username) throws ProcessExeption
    {
        return utilisateurService.getUtilisateurByUsername(username);
    }

    @GetMapping("users")
    public List<UtilisateurDTO> getAllUsers(){
        return utilisateurService.getAllUtilisateur();
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteUtilisateur(@RequestParam String username) throws ProcessExeption {
        utilisateurService.deleteUtilisateur(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("modify")
    public UtilisateurDTO modifyUtilisateur(@RequestParam String username, @RequestBody UtilisateurDTO utilisateurToModify) throws ProcessExeption {
        return  utilisateurService.modifierUtilisateur(username,utilisateurToModify);
    }

    @PutMapping("addAnime")
    public UtilisateurDTO modifyList(@RequestParam String username, @RequestBody List<Integer> listAnime) throws ProcessExeption {
        return utilisateurService.modifierUtilisateurListe(username, listAnime);
    }

    @GetMapping("isAdmin")
    public boolean isUserAnAdmin(@RequestParam String username) throws ProcessExeption {
        return utilisateurService.isUserAnAdmin(username);
    }


}
