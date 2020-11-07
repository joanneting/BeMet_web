package tw.com.BeMet.controller;

import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BeMetErrorController implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView hamdleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.SC_NOT_FOUND) {
                return new ModelAndView("error404");
            }
        }
        return new ModelAndView("error404");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
