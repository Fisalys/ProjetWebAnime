package fr.uphf.personnages.ressources;

import fr.uphf.personnages.dto.PersonnageDTO;
import fr.uphf.personnages.exeptions.ProcessExeption;
import fr.uphf.personnages.services.CommonService;
import fr.uphf.personnages.services.PersonnageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personnage")
public class PersonnageRessource extends CommonService {
    @Autowired
    PersonnageService personnageService;

    @PostMapping("create")
    public PersonnageDTO createPersonnage(@RequestBody PersonnageDTO personnageDTO) throws ProcessExeption {
        personnageService.validatePersonnageModel(personnageDTO);
        return personnageService.savePersonnage(personnageDTO);
    }
}
