package fr.uphf.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringCloudConfig {
    @Autowired
    private JwtCustomFilter customJwtFilter;
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder)
    {
        //http.csrf().disable();
        return builder.routes()
                .route(r -> r.path("/animeservice/**").filters(f-> f.filter(customJwtFilter.apply(new JwtCustomFilter.Config()))).uri("lb://anime-service"))
                .route(r -> r.path("/episodeservice/**").uri("lb://episode-service"))
                .route(r -> r.path("/personnageservice/**").uri("lb://personnage-service"))
                .route(r -> r.path("/utilisateurservice/utilisateur/**").filters(f-> f.filter(customJwtFilter.apply(new JwtCustomFilter.Config()))).uri("lb://utilisateur-service"))
                .route(r -> r.path("/utilisateurservice/authorization/**").uri("lb://utilisateur-service"))
                .build();
    }
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

}
