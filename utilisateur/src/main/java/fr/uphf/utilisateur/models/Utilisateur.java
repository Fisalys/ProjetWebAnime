package fr.uphf.utilisateur.models;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {
    @Id
    private String username;
    private String password;
    private String mail;
    private boolean admin;
    @ElementCollection
    private Map<Integer,String> listeAnime;
    @ElementCollection
    private Map<Integer,String> listePartage;
}
