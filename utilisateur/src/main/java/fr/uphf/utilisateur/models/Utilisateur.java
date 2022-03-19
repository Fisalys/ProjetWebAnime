package fr.uphf.utilisateur.models;

import lombok.*;
import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
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
    private List<Integer> listeAnime;
    @ElementCollection
    private List<String> notification;
}
