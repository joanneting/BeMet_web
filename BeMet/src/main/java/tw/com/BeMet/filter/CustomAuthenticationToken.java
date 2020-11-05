package tw.com.BeMet.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends
        UsernamePasswordAuthenticationToken {
    private String identifier;

    public CustomAuthenticationToken(String principal, String credentials, String identifier) {
        super(principal, credentials);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

}