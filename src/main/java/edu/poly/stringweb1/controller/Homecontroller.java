package edu.poly.stringweb1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import edu.poly.stringweb1.entity.ThanhVien;
import edu.poly.stringweb1.entity.ThietBi;




@Controller
@RequestMapping("/")
public class Homecontroller {

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    @GetMapping("/Quenmatkhau")
    public String QuenmatkhauPage() {
        return "Quenmatkhau";
    }
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
    @GetMapping("/datcho")
    public String datchoPage() {
        return "datcho";
    }
    @GetMapping("/qlthanhvien")
    public String qltvPage() {
        return "qlthanhvien";
    }
    @GetMapping("/quanlytb")
    public String qltbPage() {
        return "quanlytb";
    }
    @GetMapping("/xulyvp")
    public String xlvpPage() {
        return "xulyvp";
    }
    @GetMapping("/thongke")
    public String thongkePage() {
        return "thongke";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute ThanhVien thanhVien) {
        // Xử lý logic đăng ký người dùng ở đây
        // Sau khi đăng ký thành công, chuyển hướng về trang đăng nhập
        return "redirect:/login";
    }
    @PostMapping("/login")
    public String loginSuccess() {
        // Xử lý logic đăng nhập thành công ở đây
        // Sau khi đăng nhập thành công, chuyển hướng về trang "user"
        return "redirect:/user";
    }
/*    int id = -1;
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

*/
}
