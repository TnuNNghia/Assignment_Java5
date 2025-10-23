package poly.edu.asm.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asm.dao.KhachHangDAO;
import poly.edu.asm.entity.*;
import poly.edu.asm.service.DonHangService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/donhang")
public class DonHangController {

    private final DonHangService donHangService;
    private final KhachHangDAO khachHangDAO;

    // ✅ Thanh toán (tạo đơn hàng)
    @PostMapping("/checkout")
    public String thanhToan(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        KhachHang khachHang = khachHangDAO.findByUser_UserId(user.getUserId());
        if (khachHang == null) {
            return "redirect:/login";
        }

        // Tạo đơn hàng mới và lấy lại đơn hàng vừa tạo
        DonHang donHang = donHangService.taoDonHangTuGioHang(khachHang);
        if (donHang == null) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
            return "giohang";
        }

        // Chỉ cần gửi "donHang" và "message"
        model.addAttribute("donHang", donHang);
        model.addAttribute("message", "🎉 Đặt hàng thành công!");
        return "donhang-thanhcong";
    }


    // ✅ Xem tất cả đơn hàng của khách hàng
    @GetMapping
    public String xemDonHang(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        KhachHang khachHang = khachHangDAO.findByUser_UserId(user.getUserId());
        List<DonHang> list = donHangService.getDonHangByKhachHang(khachHang);

        model.addAttribute("donHangList", list);
        return "donhang-thanhcong";
    }
}
