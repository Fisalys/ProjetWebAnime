package fr.uphf.personnages.services;

import fr.uphf.personnages.dto.PersonnageDTO;
import fr.uphf.personnages.exeptions.NotValidExeption;
import fr.uphf.personnages.exeptions.ProcessExeption;
import fr.uphf.personnages.models.Personnage;
import fr.uphf.personnages.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnageService extends CommonService {

    private static final String PERSONNAGE_NOT_FOUND = "Personnage non trouvé avec l'id : %s";

    @Autowired
    private PersonnageRepository personnageRepository;

    public void validatePersonnageModel(PersonnageDTO personnageToCreate) throws NotValidExeption
    {
        NotValidExeption e = new NotValidExeption();

        if(personnageToCreate == null)
            e.getMessages().add("PersonnageModel : null");
        else{
            if(personnageToCreate.getNom() == null || personnageToCreate.getNom().isBlank())
                e.getMessages().add("Nom est vide");
        }

        if(!e.getMessages().isEmpty())
            throw e;
    }

    public PersonnageDTO savePersonnage(PersonnageDTO personnageToCreate) throws ProcessExeption
    {
        Personnage p = Personnage.builder()
                .nom(personnageToCreate.getNom())
                .prenom(personnageToCreate.getPrenom())
                .genre(personnageToCreate.getGenre())
                .idAnime(personnageToCreate.getIdAnime())
                .voice(personnageToCreate.getVoice())
                .build();

        personnageRepository.save(p);
        return personnageToCreate;
    }
}
