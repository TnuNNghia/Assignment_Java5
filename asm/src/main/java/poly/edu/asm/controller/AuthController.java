package poly.edu.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asm.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Xử lý khi người dùng ấn "Đăng ký"
    @PostMapping("/register")
    public String registerUser(
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("hoTen") String hoTen,
            @RequestParam("soDienThoai") String soDienThoai,
            @RequestParam("diaChi") String diaChi,
            Model model
    ) {
        // 🔹 Kiểm tra trống
        if (email.isBlank() || username.isBlank() || password.isBlank()
                || hoTen.isBlank() || soDienThoai.isBlank() || diaChi.isBlank()) {
            model.addAttribute("error", "Vui lòng nhập đầy đủ tất cả các trường bắt buộc!");
            return "register";
        }

        // 🔹 Kiểm tra định dạng email
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            model.addAttribute("error", "Email không hợp lệ! Vui lòng nhập lại.");
            return "register";
        }

        // 🔹 Kiểm tra độ dài mật khẩu
        if (password.length() < 6) {
            model.addAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự!");
            return "register";
        }

        // 🔹 Kiểm tra số điện thoại
        if (!soDienThoai.matches("\\d{10,11}")) {
            model.addAttribute("error", "Số điện thoại phải gồm 10 hoặc 11 chữ số!");
            return "register";
        }

        // 🔹 Gọi service để đăng ký
        String result = userService.registerUser(email, password, hoTen, soDienThoai, diaChi);

        if (result.contains("Email đã tồn tại")) {
            model.addAttribute("error", result);
            return "register";
        }

        // Nếu thành công
        model.addAttribute("message", result + " Vui lòng đăng nhập!");
        return "login";
    }
}
