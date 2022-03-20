package fr.uphf.utilisateur.payload;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnexionPayloadRequest implements Serializable {
    private String username;
    private String password;
}
