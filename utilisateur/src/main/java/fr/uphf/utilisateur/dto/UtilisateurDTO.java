package fr.uphf.utilisateur.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class UtilisateurDTO implements Serializable{
    private String username;
    private String mail;
    private boolean admin;
    private Map<Integer,String> listeAnime;
    private Map<Integer,Map<String,String>> listePartage;
}
