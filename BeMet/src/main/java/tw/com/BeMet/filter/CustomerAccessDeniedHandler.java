package tw.com.BeMet.filter;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (isAjaxRequest(request)) {
            response.resetBuffer();
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json");
            response.getWriter().println("{\"result\" : false, \"message\" : \"權限不足。\"}");
            response.flushBuffer();
        } else {
            response.sendRedirect(request.getContextPath() + "/error403");
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        System.out.println(ajaxFlag);
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
