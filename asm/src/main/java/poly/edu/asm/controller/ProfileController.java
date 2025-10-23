package poly.edu.asm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asm.dao.KhachHangDAO;
import poly.edu.asm.dao.UsersDAO;
import poly.edu.asm.entity.KhachHang;
import poly.edu.asm.entity.Users;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private KhachHangDAO khachHangDAO;

    // ✅ Hiển thị hồ sơ cá nhân
    @GetMapping
    public String showProfile(Model model, HttpSession session) {
        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        KhachHang khachHang = khachHangDAO.findByUser_UserId(loggedInUser.getUserId());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("khachHang", khachHang);
        return "profile";
    }

    // ✅ Đổi mật khẩu
    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            Model model) {

        Users loggedInUser = (Users) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        // Kiểm tra mật khẩu cũ
        if (!loggedInUser.getPasswordHash().equals(oldPassword)) {
            model.addAttribute("error", "Mật khẩu cũ không đúng!");
        } else if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp!");
        } else {
            loggedInUser.setPasswordHash(newPassword);
            usersDAO.save(loggedInUser);
            model.addAttribute("success", "Đổi mật khẩu thành công!");
        }

        // Load lại dữ liệu để hiển thị
        KhachHang khachHang = khachHangDAO.findByUser_UserId(loggedInUser.getUserId());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("khachHang", khachHang);

        return "profile";
    }
}
