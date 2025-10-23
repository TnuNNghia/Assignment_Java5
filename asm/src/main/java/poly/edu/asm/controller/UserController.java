package poly.edu.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import poly.edu.asm.service.UserService;

//@Controller
//@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    // Hiển thị form đăng ký
//    @GetMapping("/register")
//    public String showRegisterForm() {
//        return "register"; // trỏ tới file templates/register.html
//    }
//
//    // Xử lý form đăng ký
//    @PostMapping("/register")
//    public String registerUser(
//            @RequestParam("email") String email,
//            @RequestParam("password") String password,
//            @RequestParam("repassword") String repassword,
//            @RequestParam("hoTen") String hoTen,
//            @RequestParam("soDienThoai") String soDienThoai,
//            @RequestParam("diaChi") String diaChi,
//            Model model
//    ) {
//
//        // Kiểm tra nhập lại mật khẩu
//        if (!password.equals(repassword)) {
//            model.addAttribute("error", "Mật khẩu nhập lại không khớp!");
//            return "register";
//        }
//
//        try {
//            userService.registerUser(email, password, hoTen, soDienThoai, diaChi);
//            model.addAttribute("success", "Đăng ký thành công! Hãy đăng nhập.");
//            return "login"; // chuyển sang trang login.html
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("error", "Lỗi khi đăng ký tài khoản: " + e.getMessage());
//            return "register";
//        }
//    }
}
