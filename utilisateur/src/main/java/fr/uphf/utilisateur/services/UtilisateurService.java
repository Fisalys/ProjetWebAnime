package fr.uphf.utilisateur.services;

import fr.uphf.utilisateur.dto.UtilisateurDTO;
import fr.uphf.utilisateur.exeptions.NotValidExeption;
import fr.uphf.utilisateur.exeptions.ProcessExeption;
import fr.uphf.utilisateur.models.Utilisateur;
import fr.uphf.utilisateur.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService extends CommonService {

    private static final String UTILISATEUR_NOT_FOUND = "Utilisateur non trouvé avec l'username : %s";

    @Autowired
    private UtilisateurRepository utilisateurRepository;



    public UtilisateurDTO getUtilisateurByUsername(String username) throws ProcessExeption
    {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
        if(utilisateur == null)
            throw new ProcessExeption(String.format(UTILISATEUR_NOT_FOUND,username));
        UtilisateurDTO dto = UtilisateurDTO.builder()
                .username(utilisateur.getUsername())
                .mail(utilisateur.getMail())
                .admin(utilisateur.isAdmin())
                .listeAnime(utilisateur.getListeAnime())
                .notification(utilisateur.getNotification())
                .build();
        return dto;
    }

    public void validateUtilisateurModel(UtilisateurDTO utilisateurToCreate) throws NotValidExeption
    {
        NotValidExeption e = new NotValidExeption();

        if(utilisateurToCreate == null)
            e.getMessages().add("UtilisateurModel : null");
        else{
            if(utilisateurToCreate.getUsername() == null || utilisateurToCreate.getUsername().isBlank())
                e.getMessages().add("Username est vide");
            else if(utilisateurRepository.findUtilisateurByUsername(utilisateurToCreate.getUsername()) != null)
                e.getMessages().add("Username existe déja");

        }

        if(!e.getMessages().isEmpty())
            throw e;
    }

    //#TODO hasher le password
    public UtilisateurDTO saveUtilisateur(UtilisateurDTO utilisateurToCreate) throws ProcessExeption
    {

        Utilisateur u = Utilisateur.builder()
                .username(utilisateurToCreate.getUsername())
                .password(utilisateurToCreate.getMdp())
                .mail(utilisateurToCreate.getMail())
                .admin(utilisateurToCreate.isAdmin())
                .listeAnime(utilisateurToCreate.getListeAnime())
                .notification(utilisateurToCreate.getNotification())
                .build();

        utilisateurRepository.save(u);
        return utilisateurToCreate;
    }

    public List<UtilisateurDTO> getAllUtilisateur(){
        List<Utilisateur> list = this.utilisateurRepository
                .findAll();
        List<UtilisateurDTO> dto = new ArrayList<>();
        for(Utilisateur a :list){
            UtilisateurDTO d = UtilisateurDTO.builder()
                    .username(a.getUsername())
                    .mail(a.getMail())
                    .admin(a.isAdmin())
                    .listeAnime(a.getListeAnime())
                    .notification(a.getNotification())
                    .build();
            dto.add(d);
        }
        return dto;
    }

    public void deleteUtilisateur(String username)
    {
        Utilisateur u = utilisateurRepository.findUtilisateurByUsername(username);
        utilisateurRepository.delete(u);
    }

    public UtilisateurDTO modifierUtilisateur(UtilisateurDTO utilisateurDTO)
    {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(utilisateurDTO.getUsername());
        if(utilisateurDTO.getMdp() != null)
            utilisateur.setPassword(utilisateurDTO.getMdp());
        if(utilisateurDTO.getMail() != null)
            utilisateur.setMail(utilisateurDTO.getMail());
        if(utilisateurDTO.getListeAnime() != null)
            utilisateur.setListeAnime(utilisateurDTO.getListeAnime());
        if(utilisateurDTO.getNotification() != null)
            utilisateur.setNotification(utilisateurDTO.getNotification());

        utilisateurRepository.save(utilisateur);
        return utilisateurDTO;
    }

    public UtilisateurDTO modifierUtilisateurListe(UtilisateurDTO utilisateurDTO)
    {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(utilisateurDTO.getUsername());
        if(utilisateur.getListeAnime() != null)
            utilisateur.setListeAnime(utilisateurDTO.getListeAnime());

        utilisateurRepository.save(utilisateur);
        return utilisateurDTO;
    }
}
