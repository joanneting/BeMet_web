package tw.com.BeMet.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryUtility {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public static String encode(String password) {

        return encoder.encode(password);
    }

    public static boolean matches(String password, String encodePassword) {

        return encoder.matches(password, encodePassword);
    }


}
