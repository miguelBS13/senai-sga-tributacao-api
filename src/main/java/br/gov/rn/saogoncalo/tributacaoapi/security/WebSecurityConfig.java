package br.gov.rn.saogoncalo.tributacaoapi.security;

import br.gov.rn.saogoncalo.tributacaoapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuario;

    @Override //configurar autenticações
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(usuario).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override //configurar autorizações
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/teste").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override //configurar arquivos estaticos da aplicação
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
