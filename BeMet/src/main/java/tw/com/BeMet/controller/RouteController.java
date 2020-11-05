package tw.com.BeMet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RouteController {

    @GetMapping(path = "/index")
    public ModelAndView index() throws Exception {
        return new ModelAndView("index");
    }

    @GetMapping(path = "/login")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) throws Exception {
        System.out.println("error = " + error);
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("message", "登入失敗");
        } else {
            modelAndView.addObject("message", "");
        }

        return modelAndView;
    }

    @GetMapping(path = "/logout")
    public ModelAndView logoutPage() throws Exception {

        return new ModelAndView("logout");

    }

    @GetMapping(path = "/error403")
    public ModelAndView errorPage() throws Exception {
        ModelAndView modelAndView = new ModelAndView("error403");
        return modelAndView;
    }

//    @PostMapping(path = "/login", produces = "application/json;charset=UTF-8")
//    public String login(@RequestBody UserInformationBean userInformationBean) throws Exception {
//        ObjectMapper o = new ObjectMapper();
//        ObjectNode result = o.createObjectNode();
//        try {
//
//        }
//    }

}
