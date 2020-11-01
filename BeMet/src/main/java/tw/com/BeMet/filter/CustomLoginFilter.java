package tw.com.BeMet.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;
import tw.com.BeMet.dao.UserInformationDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
    private HttpSession session;

    public CustomLoginFilter(AuthenticationManager authenticationManager, UserInformationDAO userInformationDAO) {
        Assert.notNull(authenticationManager, "AuthenticationManager不能為null");
        setAuthenticationManager(authenticationManager);
        setUsernameParameter("userId");
        setPasswordParameter("password");
        setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler(userInformationDAO));
        setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 登入第一個進入點，處理頁面parameter並將此傳回後端驗證處理。
        if (request.getMethod().equals("POST")) {

            String userId = obtainUsername(request);
            String password = obtainPassword(request);
            CustomAuthenticationToken authentication;
            String identifier;
            if (request.getContentType().startsWith("application/json")) {

                authentication = resolveAuthenticationFromRequestBody(request);
            } else {
                authentication = new CustomAuthenticationToken(userId, password, "");
            }

            // 這裡將原來的UsernamePasswordAuthenticationToken換成我們自訂的CustomAuthenticationToken
            // 這裡就將token傳到後續驗證環節了
            setDetails(request, authentication);
            session = request.getSession();
            session.setAttribute("userId", userId);

            return getAuthenticationManager().authenticate(authentication);
        } else {
            throw new AuthenticationServiceException("認證方法不支援：" + request.getMethod());
        }
    }

    private CustomAuthenticationToken resolveAuthenticationFromRequestBody(HttpServletRequest request) throws AuthenticationException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            ObjectMapper o = new ObjectMapper();
            JsonNode loginRequest = o.readTree(stringBuilder.toString());
            return new CustomAuthenticationToken(
                    loginRequest.findValue("userId").asText(),
                    loginRequest.findValue("password").asText(),
                    loginRequest.findValue("identifier").asText()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationServiceException("登入失敗");
        }
    }
}
