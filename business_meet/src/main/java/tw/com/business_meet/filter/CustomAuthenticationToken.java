package tw.com.business_meet.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends
        UsernamePasswordAuthenticationToken {
    private String bluetooth;

    public CustomAuthenticationToken(String principal, String credentials, String bluetooth) {
        super(principal, credentials);
        this.bluetooth = bluetooth;
    }

    public String getBluetooth() {
        return bluetooth;
    }

}