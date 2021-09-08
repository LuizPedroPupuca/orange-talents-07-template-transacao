package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET,"transacoes")
                .hasAnyAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET,"transacoes/**")
                .hasAnyAuthority("SCOPE_escopo-proposta")
//                .antMatchers(HttpMethod.GET,"cartoes/**")
//                .hasAnyAuthority("SCOPE_escopo-proposta")
//                .antMatchers(HttpMethod.GET, "/actuator").permitAll()
//                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .anyRequest().authenticated()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

    }
}
