package fr.uphf.gateway;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO implements Serializable {
    private String username;
    private String mail;
    private String mdp;
    private boolean admin;
    private List<Integer> listeAnime;
    private List<String> notification;
}
