package edu.poly.stringweb1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import edu.poly.stringweb1.entity.ThanhVien;
import edu.poly.stringweb1.entity.ThietBi;
import edu.poly.stringweb1.service.ThanhVienService;



@Controller
@RequestMapping("/")
public class Homecontroller {
    int id = -1;
    @Autowired
    private ThanhVienService thanhVienService;

    @GetMapping
    public String HomePage() {
        return "login";
    }

    @GetMapping("/register")
    public String RegisterPage(Model model) {
        model.addAttribute("ThanhVien", new ThanhVien());
        return "register";
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(@RequestParam String username, @RequestParam String password) {
        id = Integer.parseInt(username);
        if(thanhVienService.existsByMaTVAndPassword(id, password)) {
            return "redirect:/user";
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String Register(@Valid @ModelAttribute("ThanhVien") ThanhVien tv, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        thanhVienService.SaveThanhVien(tv);
        return "redirect:/login";
    }


}
