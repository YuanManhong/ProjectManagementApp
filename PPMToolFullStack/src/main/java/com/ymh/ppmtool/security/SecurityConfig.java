package com.ymh.ppmtool.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ //securityFilterChain provides default login form
        MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(new HandlerMappingIntrospector(), "/api/projects/**");
        http
                .authorizeRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(mvcRequestMatcher)
                                        .authenticated()
                        // other configurations
                )
                .formLogin(withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                );

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailService(){
        UserDetails nemo = User.builder()
                .username("nemo")
                .password(passwordEncoder().encode("nemo"))
                .build();

        return new InMemoryUserDetailsManager(nemo);
    }
}
