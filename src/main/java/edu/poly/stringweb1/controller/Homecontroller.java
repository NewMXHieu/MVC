package edu.poly.stringweb1.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.poly.stringweb1.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.poly.stringweb1.entity.ThanhVien;

import java.math.BigInteger;
import java.util.*;

@Controller
public class Homecontroller {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

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


//    thanhvien
    @GetMapping({"","/","/qlthanhvien"})
    public String qltvPage(Model model) {
    List<ThanhVien> memberList = thanhVienRepository.findAll();
    model.addAttribute("memberList", memberList);
    return "qlthanhvien";
}


//add thanhvien
    @PostMapping("/themThanhVien")
    @ResponseBody
    public Map<String, Object> themTV(@RequestBody Map<String, String> thanhVien) {
        Map<String, Object> map = new HashMap<>();

        // Kiểm tra các trường dữ liệu không được để trống
        if (thanhVien.get("maTV") == null || thanhVien.get("maTV").isEmpty() ||
                thanhVien.get("hoTen") == null || thanhVien.get("hoTen").isEmpty() ||
                thanhVien.get("khoa") == null || thanhVien.get("khoa").isEmpty() ||
                thanhVien.get("nganh") == null || thanhVien.get("nganh").isEmpty() ||
                thanhVien.get("sdt") == null || thanhVien.get("sdt").isEmpty() ||
                thanhVien.get("password") == null || thanhVien.get("password").isEmpty() ||
                thanhVien.get("email") == null || thanhVien.get("email").isEmpty()) {

            map.put("success", false);
            map.put("message", "Không được bỏ trống");
            return map;
        }

        try {
            int maTV = Integer.parseInt(thanhVien.get("maTV"));
            String tenTV = thanhVien.get("hoTen"); // Sửa từ "tenTV" thành "hoTen"
            String khoa = thanhVien.get("khoa");
            String nganh = thanhVien.get("nganh");
            String sdt = thanhVien.get("sdt");
            String password = thanhVien.get("password");
            String email = thanhVien.get("email");
            String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

            // Kiểm tra định dạng email
            if (!email.matches(gmailRegex)) {
                map.put("success", false);
                map.put("message", "Email không đúng định dạng");
                return map;
            }
            // Kiểm tra xem mã thành viên, email, số điện thoại và tên thành viên có tồn tại hay không
            ThanhVien existMaTV = thanhVienRepository.findByMaTV(maTV);
            ThanhVien exitsEmail = thanhVienRepository.findByEmail(email);
            ThanhVien exitsPhone = thanhVienRepository.findBySdt(sdt);

            if (existMaTV != null) {
                map.put("message", "Mã thành viên đã tồn tại");
                map.put("success", false);
                return map;
            }
            if (exitsEmail != null) {
                map.put("message", "Email đã tồn tại");
                map.put("success", false);
                return map;
            }
            if (exitsPhone != null) {
                map.put("message", "Số điện thoại đã tồn tại");
                map.put("success", false);
                return map;
            }

            // Tạo mới thành viên và lưu vào cơ sở dữ liệu
            ThanhVien newMember = new ThanhVien(maTV, tenTV, khoa, nganh, sdt, password, email);
            ThanhVien addedMember = thanhVienRepository.save(newMember);

            map.put("success", true);
            map.put("message", "Thêm thành viên thành công");
        } catch (NumberFormatException e) {
            map.put("success", false);
            map.put("message", "Mã thành viên phải là một số nguyên hợp lệ");
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "Có lỗi xảy ra: " + e.getMessage());
        }

        return map;
    }


    @PostMapping("/deleteMember")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteMembersByIds(@RequestBody List<Integer> maTVList) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (maTVList == null || maTVList.isEmpty()) {
                response.put("success", false);
                response.put("message", "Danh sách mã thành viên rỗng hoặc không tồn tại");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            List<String> failedToDelete = new ArrayList<>();
            for (Integer maTV : maTVList) {
                ThanhVien thanhVien = thanhVienRepository.findByMaTV(maTV);
                if (thanhVien == null) {
                    failedToDelete.add(maTV.toString());
                } else {
                    thanhVienRepository.delete(thanhVien);
                }
            }

            if (failedToDelete.isEmpty()) {
                response.put("success", true);
                response.put("message", "Xóa thành công tất cả thành viên được chọn");
            } else {
                response.put("success", false);
                response.put("message", "Không thể xóa các thành viên có mã: " + String.join(", ", failedToDelete));
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Xóa thất bại");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/editMember")
    @ResponseBody
    public Map<String, Object> editMember(@RequestBody Map<String, String> thanhVien) {
        Map<String, Object> map = new HashMap<>();

        // Kiểm tra dữ liệu đầu vào
        if (thanhVien.get("maTV") == null || thanhVien.get("maTV").isEmpty() ||
                thanhVien.get("tenTV") == null || thanhVien.get("tenTV").isEmpty() ||
                thanhVien.get("khoa") == null || thanhVien.get("khoa").isEmpty() ||
                thanhVien.get("nganh") == null || thanhVien.get("nganh").isEmpty() ||
                thanhVien.get("sdt") == null || thanhVien.get("sdt").isEmpty() ||
                thanhVien.get("password") == null || thanhVien.get("password").isEmpty() ||
                thanhVien.get("email") == null || thanhVien.get("email").isEmpty()) {

            map.put("success", false);
            map.put("message", "Không được để trống các trường");
            return map;
        }

        int maTV = Integer.parseInt((String) thanhVien.get("maTV"));
        String tenTV = String.valueOf(thanhVien.get("tenTV"));
        String khoa = String.valueOf(thanhVien.get("khoa"));
        String nganh = String.valueOf(thanhVien.get("nganh"));
        String sdt = String.valueOf(thanhVien.get("sdt"));
        String password = String.valueOf(thanhVien.get("password"));
        String email = String.valueOf(thanhVien.get("email"));
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

        // Kiểm tra định dạng email
        if (!email.matches(gmailRegex)) {
            map.put("success", false);
            map.put("message", "Email không đúng định dạng");
            return map;
        }
        ThanhVien existingMemberByMaTV = thanhVienRepository.findByMaTV(maTV);
        if (existingMemberByMaTV == null) {
            map.put("message", "Không tìm thấy thành viên với mã " + maTV);
            map.put("success", false);
            return map;
        }

        ThanhVien updateMember = new ThanhVien(maTV, tenTV, khoa, nganh, sdt, password, email);
        ThanhVien result = thanhVienRepository.save(updateMember);

        map.put("success", result != null);
        if (result != null) {
            map.put("message", "Cập nhật thành viên thành công");
        }
        return map;
    }

    @PostMapping("/getMember")
    @ResponseBody
    @JsonIgnoreProperties
    public ThanhVien getMemberById(@RequestBody Map<String, String> requestData) {
        int maTV = Integer.parseInt((String) requestData.get("maTV"));
        ThanhVien thanhvien = thanhVienRepository.findByMaTV(maTV);
        return thanhvien;
    }

    @GetMapping("/searchMember")
    @ResponseBody
    public List<ThanhVien> searchMemberByName(@RequestParam String tenTV) {
        // Khởi tạo danh sách để lưu trữ kết quả tìm kiếm
        List<ThanhVien> resultList = new ArrayList<>();

        // Tìm kiếm thành viên theo tên (hoặc phần của tên)
        List<ThanhVien> matchingMembers = (List<ThanhVien>) thanhVienRepository.findByHoTenContainingIgnoreCase(tenTV);

        // Kiểm tra xem matchingMembers có null hay không
        if (matchingMembers != null) {
            // Thêm các thành viên tìm được vào danh sách kết quả
            resultList.addAll(matchingMembers);
        }

        // Trả về danh sách kết quả
        return resultList;
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
