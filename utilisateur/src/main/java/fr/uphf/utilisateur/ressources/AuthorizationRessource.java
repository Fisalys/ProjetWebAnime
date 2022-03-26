package fr.uphf.utilisateur.ressources;

import fr.uphf.utilisateur.config.JwtTokenProvider;
import fr.uphf.utilisateur.dto.UtilisateurDTO;
import fr.uphf.utilisateur.exeptions.ProcessExeption;
import fr.uphf.utilisateur.models.Utilisateur;
import fr.uphf.utilisateur.payload.ConnexionPayloadRequest;
import fr.uphf.utilisateur.payload.ConnexionPayloadResponse;
import fr.uphf.utilisateur.repositories.UtilisateurRepository;
import fr.uphf.utilisateur.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("authorization")
public class AuthorizationRessource {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    UtilisateurService utilisateurService;
    @PostMapping("register")
    public ResponseEntity<UtilisateurDTO> register(@RequestBody UtilisateurDTO inscriptionPayloadRequest) throws ProcessExeption {
        utilisateurService.validateUtilisateurModel(inscriptionPayloadRequest);
        return ResponseEntity.ok(utilisateurService.saveUtilisateur(inscriptionPayloadRequest));
    }

    @PostMapping("login")
    public ResponseEntity<ConnexionPayloadResponse> login(@RequestBody ConnexionPayloadRequest loginPayloadRequest) {
        return utilisateurRepository.findUtilisateurByUsernameAndPassword(loginPayloadRequest.getUsername(), loginPayloadRequest.getPassword())
                .map(utilisateur -> ResponseEntity.ok(jwtTokenProvider.generateToken(utilisateur.getUsername())))
                .orElse(new ResponseEntity("Username ou mot de passe invalide", HttpStatus.UNAUTHORIZED));
    }

    //@GetMapping("is-authorized")
    public ResponseEntity isAuthorize(@RequestHeader("Authorization") String jwt) {
        System.out.println("test");
        if(StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
            if (jwtTokenProvider.validateToken(jwt)) {
                String username = jwtTokenProvider.getUserIdFromJWT(jwt);
                Utilisateur user = utilisateurRepository.findUtilisateurByUsername(username);
                if(user != null) {
                    return ResponseEntity.ok().build();
                }
            }
        }
        return new ResponseEntity("Vous n'êtes pas autorisé à appeler la ressource", HttpStatus.UNAUTHORIZED);
    }
}
