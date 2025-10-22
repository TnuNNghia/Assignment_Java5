package poly.edu.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import poly.edu.asm.dao.SanPhamDAO;
import poly.edu.asm.entity.SanPham;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SanPhamDAO sanPhamDAO;

    // Trang chủ
    @GetMapping({"/", "/trangchu"})
    public String trangChu(Model model) {
        // Lấy 6 sản phẩm đầu tiên để hiển thị ở trang chủ
        List<SanPham> top6SanPham = sanPhamDAO.findTop6();
        model.addAttribute("products", top6SanPham);
        return "trangchu1";
    }
    // Trang giới thiệu thương hiệu
    @GetMapping("/thuonghieu")
    public String gioiThieuThuongHieu() {
        return "thuonghieu"; // file veThuongHieu.html trong /templates
    }
    // Trang giới thiệu tin tức
    @GetMapping("/tintuc")
    public String tintuc(Model model) {
        return "tintuc"; // file tientuc.html trong /templates
    }
    // Trang giới thiệu liên hệ
    @GetMapping("/lienhe")
    public String lienhe(Model model) {
        return "lienhe"; // file lienhe.html trong /templates
    }
    // Trang giới thiệu login
    @GetMapping("/login")
    public String login(Model model) {
        return "login"; // file login.html trong /templates
    }
    @GetMapping("/newTK")
    public String newTK(Model model) {
        return "newTK";
    }
}
