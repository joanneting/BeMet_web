package tw.com.business_meet.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tw.com.business_meet.dao.UserInformationDAO;
import tw.com.business_meet.utils.BCryUtility;
import tw.com.business_meet.vo.UserInformation;

import javax.transaction.Transactional;

//身分驗證
@Transactional
@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserInformationDAO userInformationDAO;

    public CustomAuthenticationProvider() {
        setHideUserNotFoundExceptions(false);
    }

    @Transactional
    @Override
    protected UserDetails retrieveUser(String userId,
                                       UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 登入第二個進入點，由tw.com.SpringSecurity.filter.CustomLoginFilter傳來，處理登入問題。
        // 登入邏輯
        CustomAuthenticationToken customAuthenticationToken =
                ((CustomAuthenticationToken) authentication);

        String blueTooth = customAuthenticationToken.getBluetooth();
        String password = (String) customAuthenticationToken.getCredentials();
        UserInformation user = userInformationDAO.getById(userId);

        if (blueTooth != "" && !user.getBluetooth().equals(blueTooth)) {
            throw new AuthenticationServiceException("請確認該帳號的藍芽是目前手機的藍芽。");
        }
        if (user != null && !user.getUserId().isEmpty()
                && BCryUtility.matches(password, user.getPassword())) {
//            System.out.println(user.getRoleNo());
            user.setAuthorities();
            System.out.println(user.getAuthorities());
            return user;

        }

        throw new AuthenticationServiceException("請檢查帳號或是密碼是否有錯誤。");

    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 登入第三個進入點，如果想做點額外的檢查,可以在這個方法裡處理,校驗不通時,直接拋異常即可
    }
}
