package com.jdacamacho.hexagonal.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdacamacho.hexagonal.Infrastucture.JWT.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                            .csrf(csrf -> csrf
                                            .disable())
                            .authorizeHttpRequests(authRequest -> authRequest
                                            .requestMatchers("/api/auth").permitAll()
                                            
                                            .requestMatchers(HttpMethod.GET, "/api/users/adm").hasRole("Administrator") 
                                            .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("Administrator", "Client") 
                                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                                            .requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasAnyRole("Administrator", "Client") 
                                            
                                            .requestMatchers(HttpMethod.GET, "/api/owners/adm").hasRole("Administrator")
                                            .requestMatchers(HttpMethod.GET, "/api/owners/{id}").hasAnyRole("Administrator", "Owner")
                                            .requestMatchers(HttpMethod.POST, "/api/owners").permitAll()
                                            .requestMatchers(HttpMethod.PUT, "/api/owners/{id}").hasAnyRole("Administrator", "Owner")
                                            
                                            .requestMatchers("/api/fields/adm").hasRole("Administrator")
                                            .requestMatchers(HttpMethod.GET, "/api/fields/{id}").hasAnyRole("Administrator", "Owner")
                                            .requestMatchers(HttpMethod.GET, "/api/fields/owners/id/{id}").hasAnyRole("Administrator", "Owner")
                                            .requestMatchers(HttpMethod.GET, "/api/fields/name/").hasAnyRole("Administrator", "Client")
                                            .requestMatchers(HttpMethod.GET, "/api/fields/schedules/{id}").hasAnyRole("Administrator", "Owner", "Client")
                                            .requestMatchers(HttpMethod.GET, "/api/fields/schedules/enable/{id}").hasAnyRole("Administrator", "Owner")
                                            .requestMatchers(HttpMethod.POST, "/api/fields/{idOwner}").hasAnyRole("Administrator", "Owner")
                                            .requestMatchers(HttpMethod.PUT, "/api/fields/{id}").hasAnyRole("Administrator", "Owner")
                                            .requestMatchers(HttpMethod.DELETE, "/api/fields/{id}").hasAnyRole("Administrator", "Owner")
                                            
                                            .requestMatchers("/api/reservations/adm").hasRole("Administrator")
                                            .requestMatchers(HttpMethod.GET, "/api/reservations/users/{id}").hasAnyRole("Administrator", "Client")
                                            .requestMatchers(HttpMethod.GET, "/api/reservations/adm/users/name/").hasRole("Administrator")
                                            .requestMatchers(HttpMethod.POST, "/api/reservations/users/{idUser}/fields/{idField}/schedules/{idSchedule}").hasAnyRole("Administrator", "Client")
                                            .requestMatchers(HttpMethod.PATCH, "/api/reservations/{id}").hasRole("Administrator")
                                            .requestMatchers(HttpMethod.DELETE, "/api/reservations/{id}").hasAnyRole("Administrator", "Client")
                                            
                                            .anyRequest().authenticated()
                            )
                            .sessionManagement(sessionManager -> sessionManager
                                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                            .authenticationProvider(authenticationProvider)
                            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                            .build();
    }
        
}
