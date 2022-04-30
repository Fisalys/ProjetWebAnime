package fr.uphf.personnages.services;

import fr.uphf.personnages.dto.PersonnageDTO;
import fr.uphf.personnages.exeptions.NotValidExeption;
import fr.uphf.personnages.exeptions.ProcessExeption;
import fr.uphf.personnages.models.Personnage;
import fr.uphf.personnages.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnageService extends CommonService {

    private static final String PERSONNAGE_NOT_FOUND = "Personnage non trouvé avec l'id : %s";

    @Autowired
    public RestTemplate restTemplate;

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

    public PersonnageDTO getPersonnage(Integer id) throws ProcessExeption {
       Optional<Personnage> pe =  personnageRepository.findById(id);
       if(pe.isEmpty())
           throw new ProcessExeption(String.format(PERSONNAGE_NOT_FOUND,id));
       Personnage p= pe.get();
       return PersonnageDTO.builder()
                .idPersonnage(p.getIdPersonnage())
                .nom(p.getNom())
                .prenom(p.getPrenom())
                .genre(p.getGenre())
                .idAnime(p.getIdAnime())
                .voice(p.getVoice())
                .build();
    }

    public List<PersonnageDTO> getPersonnagesFromAnime(Integer idAnime) throws ProcessExeption {
        List<Personnage> listPersonnage = personnageRepository.findPersonnagesByIdAnime(idAnime);
        if(listPersonnage == null)
            throw new ProcessExeption("Aucun personnage trouve pour l'anime");

        List<PersonnageDTO> list = new ArrayList<>();
        for(Personnage p : listPersonnage)
        {
            list.add(PersonnageDTO.builder()
                    .idPersonnage(p.getIdPersonnage())
                    .nom(p.getNom())
                    .prenom(p.getPrenom())
                    .genre(p.getGenre())
                    .idAnime(p.getIdAnime())
                    .voice(p.getVoice())
                    .build());
        }

        return list;
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

        restTemplate.put("http://anime-service/animeservice/admin/addPersonnage?idAnime=" + personnageToCreate.getIdAnime() + "&idPersonnage=" + p.getIdPersonnage(), null);
        personnageToCreate.setIdPersonnage(p.getIdPersonnage());
        return personnageToCreate;
    }

    public PersonnageDTO modifierPersonnage(PersonnageDTO personnageDTO)
    {
        Personnage personnage = personnageRepository.getById(personnageDTO.getIdPersonnage());
        if(personnageDTO.getNom() != null)
            personnage.setNom(personnageDTO.getNom());
        if(personnageDTO.getPrenom() != null)
            personnage.setPrenom(personnageDTO.getPrenom());
        if(personnageDTO.getGenre() != null)
            personnage.setGenre(personnageDTO.getGenre());
        if(personnageDTO.getIdAnime() != null)
            personnage.setIdAnime(personnageDTO.getIdAnime());
        if(personnageDTO.getVoice() != null)
            personnage.setVoice(personnageDTO.getVoice());

        personnageRepository.save(personnage);
        return personnageDTO;
    }
}
