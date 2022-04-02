package fr.uphf.personnages.ressources;

import fr.uphf.personnages.dto.PersonnageDTO;
import fr.uphf.personnages.exeptions.ProcessExeption;
import fr.uphf.personnages.services.PersonnageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminRessource {
    @Autowired
    PersonnageService personnageService;

    @PostMapping("create")
    public PersonnageDTO createPersonnage(@RequestBody PersonnageDTO personnageDTO) throws ProcessExeption {
        personnageService.validatePersonnageModel(personnageDTO);
        return personnageService.savePersonnage(personnageDTO);
    }

    @PutMapping
    PersonnageDTO modifierPersonnage(@RequestBody PersonnageDTO personnageDTO)
    {
        return personnageService.modifierPersonnage(personnageDTO);
    }
}
