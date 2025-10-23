package poly.edu.asm.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asm.dao.KhachHangDAO;
import poly.edu.asm.entity.*;
import poly.edu.asm.service.GioHangService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/giohang")
public class GioHangController {

    private final GioHangService gioHangService;
    private final KhachHangDAO khachHangDAO;

    // 🧺 Hiển thị giỏ hàng
    @GetMapping
    public String xemGioHang(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        KhachHang khachHang = khachHangDAO.findByUser_UserId(user.getUserId());
        if (khachHang == null) return "redirect:/login";

        GioHang gioHang = gioHangService.getGioHangByKhachHang(khachHang);
        model.addAttribute("gioHang", gioHang);
        model.addAttribute("chiTietGioHang", gioHangService.getChiTietByGioHang(gioHang));

        return "giohang";
    }

    // ➕ Thêm sản phẩm
    @PostMapping("/add")
    public String themVaoGio(@RequestParam Integer maSanPham,
                             @RequestParam(defaultValue = "1") Integer soLuong,
                             HttpSession session) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        KhachHang khachHang = khachHangDAO.findByUser_UserId(user.getUserId());
        if (khachHang == null) return "redirect:/login";

        SanPham sanPham = new SanPham();
        sanPham.setMaSanPham(maSanPham);

        GioHang gioHang = gioHangService.getGioHangByKhachHang(khachHang);
        gioHangService.addSanPhamToGioHang(gioHang, sanPham, soLuong);

        return "redirect:/giohang";
    }

    // ❌ Xóa sản phẩm khỏi giỏ
    @GetMapping("/remove/{id}")
    public String xoaKhoiGio(@PathVariable("id") Integer maChiTietGioHang,
                             HttpSession session) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        gioHangService.removeSanPhamFromGioHang(maChiTietGioHang);
        return "redirect:/giohang";
    }
}
