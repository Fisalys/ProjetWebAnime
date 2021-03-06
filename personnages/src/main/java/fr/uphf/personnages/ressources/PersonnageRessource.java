package fr.uphf.personnages.ressources;

import fr.uphf.personnages.dto.PersonnageDTO;
import fr.uphf.personnages.exeptions.ProcessExeption;
import fr.uphf.personnages.services.CommonService;
import fr.uphf.personnages.services.PersonnageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personnage")
public class PersonnageRessource extends CommonService {
    @Autowired
    PersonnageService personnageService;

    @GetMapping("persofromanime")
    public List<PersonnageDTO> getPersoFromAnime(@RequestParam Integer idAnime) throws ProcessExeption {
        return personnageService.getPersonnagesFromAnime(idAnime);
    }

    @GetMapping("perso")
    public PersonnageDTO getPersoById(@RequestParam Integer id) throws ProcessExeption {
        return personnageService.getPersonnage(id);
    }
}
