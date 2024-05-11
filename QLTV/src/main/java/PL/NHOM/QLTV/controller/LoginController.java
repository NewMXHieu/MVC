package PL.NHOM.QLTV.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginPage(){
        return "auth-login";
    }

    @RequestMapping("/home")
    public String loginSubmit(){
        return "/pages/landing_page";
    }



    @GetMapping("/xulyvipham")
    public String xulyvipham(){return "/pages/HandlingOfViolations";}


    @RequestMapping("/QLThanhvien")
    public String QlThanhvien() { return "/pages/component-qlthanhvien";}

    @RequestMapping("/QLThietbi")
    public String QlThietbi() { return "/pages/component-qlthietbi";}

}
