package br.com.fujideia.iesp.tecback.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "moviedb")
public class MovieApiConfig {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String secret) {
        this.token = secret;
    }
}
