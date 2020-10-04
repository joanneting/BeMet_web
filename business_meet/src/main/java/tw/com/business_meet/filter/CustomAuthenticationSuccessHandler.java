package tw.com.business_meet.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tw.com.business_meet.bean.UserInformationBean;
import tw.com.business_meet.dao.UserInformationDAO;
import tw.com.business_meet.service.UserInformationService;
import tw.com.business_meet.utils.BeanUtility;
import tw.com.business_meet.utils.ResponseUtils;
import tw.com.business_meet.vo.UserInformation;

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
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("userInformationDAO = " + userInformationDAO);
        UserInformation userInformation = userInformationDAO.getById(userDetails.getUsername());
        UserInformationBean userInformationBean = new UserInformationBean();
        BeanUtility.copyProperties(userInformation,userInformationBean);
        ResponseUtils.response(
                response,
                200,
                true,
                "",
                "登入成功",
                mapper.createObjectNode().put("identity", authorityName).putPOJO("userInformationBean",userInformationBean)
        );
    }


}
