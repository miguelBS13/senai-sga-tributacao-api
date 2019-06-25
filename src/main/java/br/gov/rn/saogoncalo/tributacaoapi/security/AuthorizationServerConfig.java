package br.gov.rn.saogoncalo.tributacaoapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override //Configurações do Cliente
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory()
              .withClient("angular")//nome do cliente que vai solicitar o token
              .secret("$2a$10$C6FTBk1ZuYaffCMGCJvUSO8BAQP3lYF9Ln3nalmbqHNN/CCh35iNa")//senha do cliente que solicita o token
              .scopes("read", "write")//niveis de permissão
              .authorizedGrantTypes("password")//tipo de token
              .accessTokenValiditySeconds(600);//validade em segundos do token
    }

    @Override //Configurações do Token
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("sga-tributacao");
        return converter;
    }

}
