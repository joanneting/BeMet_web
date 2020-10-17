package tw.com.business_meet.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import tw.com.business_meet.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();
        ObjectMapper mapper = new ObjectMapper();

        ResponseUtils.response(
                response,
                401,
                false,
                "",
                exception.getMessage(),
                mapper.createObjectNode()
        );
    }


}
