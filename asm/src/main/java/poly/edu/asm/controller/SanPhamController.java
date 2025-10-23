package poly.edu.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import poly.edu.asm.dao.SanPhamDAO;
import poly.edu.asm.dao.LoaiDAO;
import poly.edu.asm.dao.NhaCungCapDAO;
import poly.edu.asm.entity.SanPham;
import poly.edu.asm.entity.Users;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamDAO sanPhamDAO;

    @Autowired
    private LoaiDAO loaiDAO;

    @Autowired
    private NhaCungCapDAO nhaCungCapDAO;

    @Autowired
    private HttpSession session;

    // 🟢 Lấy danh sách ảnh trong static/img
    private List<String> getImageList() {
        try {
            File folder = new ClassPathResource("static/img").getFile();
            if (folder.exists() && folder.isDirectory()) {
                return Arrays.stream(folder.listFiles())
                        .filter(File::isFile)
                        .map(File::getName)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    // ======== HIỂN THỊ TẤT CẢ SẢN PHẨM ========
    @GetMapping
    public String tatCaSanPham(Model model) {
        List<SanPham> sanPhamList = sanPhamDAO.findAll();
        model.addAttribute("products", sanPhamList);
        return "SanPham";
    }

    // ======== CHI TIẾT SẢN PHẨM ========
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable("id") Integer id, Model model) {
        SanPham sp = sanPhamDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm có ID: " + id));
        model.addAttribute("sp", sp);
        return "ctsp";
    }

    // 🟡 Hiển thị form thêm sản phẩm (phải đăng nhập)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login"; // 🔒 chưa đăng nhập thì chặn

        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("dsLoai", loaiDAO.findAll());
        model.addAttribute("dsNCC", nhaCungCapDAO.findAll());
        model.addAttribute("dsHinh", getImageList());
        return "sanpham_form";
    }

    // 🟣 Hiển thị form sửa sản phẩm (phải đăng nhập)
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        SanPham sanPham = sanPhamDAO.findById(id).orElse(null);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("dsLoai", loaiDAO.findAll());
        model.addAttribute("dsNCC", nhaCungCapDAO.findAll());
        model.addAttribute("dsHinh", getImageList());
        return "sanpham_form";
    }

    // 🔵 Xóa sản phẩm (phải đăng nhập)
    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") Integer id, Model model) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        try {
            sanPhamDAO.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không thể xóa sản phẩm vì đang được tham chiếu trong bảng tồn kho!");
            List<SanPham> sanPhamList = sanPhamDAO.findAll();
            model.addAttribute("products", sanPhamList);
            return "sanpham";
        }
        return "redirect:/sanpham";
    }

    // 🟢 Lưu hoặc cập nhật sản phẩm (phải đăng nhập)
    @PostMapping("/save")
    public String addOrUpdateSanPham(@ModelAttribute SanPham sanPham) {
        Users user = (Users) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        try {
            if (sanPham.getMaSanPham() != null) {
                SanPham existing = sanPhamDAO.findById(sanPham.getMaSanPham()).orElse(null);
                if (existing != null && (sanPham.getHinhAnh() == null || sanPham.getHinhAnh().isEmpty())) {
                    sanPham.setHinhAnh(existing.getHinhAnh());
                }
            }

            sanPhamDAO.save(sanPham);
            return "redirect:/sanpham";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
