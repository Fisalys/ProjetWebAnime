package fr.uphf.gateway;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class JwtCustomFilter extends AbstractGatewayFilterFactory<JwtCustomFilter.Config> {

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    private static final Logger logger = LoggerFactory.getLogger(JwtCustomFilter.class);

    @Autowired
    public RestTemplate restTemplate;

    public WebClient webClient = WebClient.create();

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            System.out.println(config.admin);
            if(!config.admin) {
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION) ||
                        !(isAuthorize(request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0)).getStatusCode() == HttpStatus.OK)) {
                    return onError(exchange, "Not Authorized!");
                }
            }
            else {
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION) ||
                        !(isAuthorize(request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0)).getStatusCode() == HttpStatus.OK) ||
                        !(getAdminFromJWT(request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0)))) {
                   return onError(exchange, "Not Authorized!");
                }
            }
            return chain.filter(exchange);
        });
    }

    // https://spring.io/reactive
    private Mono<Void> onError(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }

    public ResponseEntity isAuthorize(String jwt) {
        System.out.println("test");
        if(StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
            if (validateToken(jwt)) {
                return ResponseEntity.ok().build();
            }
        }
        return new ResponseEntity("Vous n'êtes pas autorisé à appeler la ressource", HttpStatus.UNAUTHORIZED);
    }

    public String getUserIdFromJWT(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean getAdminFromJWT(String token) {
        token = token.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("admin", Boolean.class);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
    @Getter
    @Setter
    @Builder
    public static class Config {
        //Put the configuration properties for your filter here
        private boolean admin;

        public Config() {
            admin = false;
        }

        public Config(boolean admin)
        {
            this.admin = admin;
        }
    }

}
