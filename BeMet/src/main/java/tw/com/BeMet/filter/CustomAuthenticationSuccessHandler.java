package tw.com.BeMet.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tw.com.BeMet.bean.UserInformationBean;
import tw.com.BeMet.dao.UserInformationDAO;
import tw.com.BeMet.utils.BeanUtility;
import tw.com.BeMet.utils.ResponseUtils;
import tw.com.BeMet.vo.UserInformation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserInformationDAO userInformationDAO;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserInformationDAO userInformationDAO) {
        this.userInformationDAO = userInformationDAO;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ObjectMapper mapper = new ObjectMapper();
        List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) authentication.getAuthorities();
        GrantedAuthority grantedAuthority = authorities.get(0);
        String authorityName = grantedAuthority.getAuthority();
        UserInformation userInformation = userInformationDAO.getById(userDetails.getUsername());
        request.getSession().setAttribute("userName", userInformation.getName());
        request.getSession().setAttribute("roleNo", userInformation.getRoleNo());
        UserInformationBean userInformationBean = new UserInformationBean();
        BeanUtility.copyProperties(userInformation, userInformationBean);
        if (request.getContentType().startsWith("application/json")) {
            ResponseUtils.response(
                    response,
                    200,
                    true,
                    "",
                    "登入成功",
                    mapper.createObjectNode().put("identity", authorityName).putPOJO("userInformationBean", userInformationBean)
            );
        } else {
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                if (authority.getAuthority().equals("user")) {
                    response.sendRedirect("problemreport/add");
                } else {
                    response.sendRedirect("problemreport/");
                }
            }

        }

    }
}
