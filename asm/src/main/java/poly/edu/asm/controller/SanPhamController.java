package poly.edu.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.asm.dao.SanPhamDAO;
import poly.edu.asm.entity.SanPham;
import java.util.List;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamDAO sanPhamDAO;

    @GetMapping
    public String tatCaSanPham(Model model) {
        List<SanPham> sanPhamList = sanPhamDAO.findAll();
        model.addAttribute("products", sanPhamList);
        return "SanPham";
    }
}
