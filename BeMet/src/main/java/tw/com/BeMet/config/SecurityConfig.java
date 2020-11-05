package tw.com.BeMet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import tw.com.BeMet.dao.UserInformationDAO;
import tw.com.BeMet.filter.CustomAuthenticationProvider;
import tw.com.BeMet.filter.CustomEntryPoint;
import tw.com.BeMet.filter.CustomLoginFilter;
import tw.com.BeMet.filter.CustomerAccessDeniedHandler;
import tw.com.BeMet.service.UserInformationService;

import javax.sql.DataSource;
import javax.transaction.Transactional;

// 參考連結：https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 表示可使用@PreAuthority註解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    @Autowired
    private UserInformationDAO userInformationDAO;

    // private UserAccountService userService;//帳號
    @Autowired
    public SecurityConfig(DataSource dataSource, UserInformationService userService) {
        Assert.notNull(dataSource, "dataSource不能為null");
        // Assert.notNull(userService, "userService不能為null");
        this.dataSource = dataSource;
        // this.userService = userService;
    }

    // 這個表示哪些頁面"不會用到SpringSecurity"，相當於xml中的security="none"
    // 代表在這些連結中會抓不到登入資訊
    // 即SpringContextHolder.getContext() = null
    // 因此這些只能用在靜態資源上
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET, "/static/**", "/image/**");
    }

    @Transactional
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling()// 出錯時的例外處理
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))// 未登入處理
                .accessDeniedHandler(new CustomerAccessDeniedHandler())// 偵測權限不足的處理
                .authenticationEntryPoint(new CustomEntryPoint())//自定義未登入處理
                .and().authenticationProvider(new CustomAuthenticationProvider())// 內部是寫add，所以可以多個//權限設定，額外登入判斷處理
                .addFilterBefore(new CustomLoginFilter(authenticationManager(), userInformationDAO),
                        UsernamePasswordAuthenticationFilter.class)//取request傳送的值//設定登入驗證參數//設定自定義登入(失敗&成功)處理
                .authorizeRequests()// 設定Requests的權限需求
                .antMatchers(HttpMethod.GET, "/logout", "/timeout", "/error**", "/login*")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/userinformation/add").permitAll()
                .antMatchers("/index").permitAll()
                .anyRequest()// 表示除了上述請求，都需要權限
                .authenticated()
                .and()
                .formLogin()// 設定Login，如果是用Form表單登入的話
                .loginPage("/login")// 設定Login頁面的URL
                .loginProcessingUrl("/login")// 設定Login動作的URL
                .successForwardUrl("/index")
                .failureUrl("/login?error")// 設定Login失敗的URL
                .permitAll()// Login不需要權限
                .and()
                .logout()// 設定Logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "GET"))// 設定Logout
                // URL
                // .invalidateHttpSession(true)
                .logoutSuccessUrl("/logout")// 設定登出成功後的URL
                .deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()// Session管理
                .sessionFixation()// Session固定ID保護
                // .newSession()// 產生新的，不複製
                // .none()// 不會產生新的
                .migrateSession()// 每次登入，都會產生新的，並將舊的屬性複製，預設值
//                .invalidSessionUrl("/timeout")// Session過期時的URL導向
                .maximumSessions(1)// 設定Session數只能一個
//                .expiredUrl("/timeout")// 設定因為再次登入而導致的URL過期的URL導向
                .and()
                .and()
                .rememberMe().tokenValiditySeconds(Integer.MAX_VALUE)//設定保持長時間登入
        ;
    }


}

