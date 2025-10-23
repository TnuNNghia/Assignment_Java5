package poly.edu.asm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asm.dao.UsersDAO;
import poly.edu.asm.entity.Users;

@Controller
public class LoginController {

    @Autowired
    private UsersDAO usersDAO;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("passwordHash") String password,
                        Model model,
                        HttpSession session) {

        Users user = usersDAO.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "Email không tồn tại!");
            return "login";
        }

        if (!user.getPasswordHash().equals(password)) {
            model.addAttribute("error", "Sai mật khẩu!");
            return "login";
        }

        // ✅ Lưu user vào session
        session.setAttribute("loggedInUser", user);

        // ✅ Chuyển hướng về trang chủ
        return "redirect:/";
    }

    // ✅ Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // xóa session
        return "redirect:/login";
    }
}
