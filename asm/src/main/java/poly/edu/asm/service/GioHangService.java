package poly.edu.asm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poly.edu.asm.dao.*;
import poly.edu.asm.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GioHangService {

    private final GioHangDAO gioHangDAO;
    private final ChiTietGioHangDAO chiTietGioHangDAO;

    // Lấy giỏ hàng theo khách hàng (tạo mới nếu chưa có)
    public GioHang getGioHangByKhachHang(KhachHang khachHang) {
        GioHang gioHang = gioHangDAO.findByKhachHang_MaKhachHang(khachHang.getMaKhachHang());
        if (gioHang == null) {
            gioHang = new GioHang();
            gioHang.setKhachHang(khachHang);
            gioHangDAO.save(gioHang);
        }
        return gioHang;
    }

    // Thêm sản phẩm vào giỏ
    public void addSanPhamToGioHang(GioHang gioHang, SanPham sanPham, int soLuong) {
        List<ChiTietGioHang> list = chiTietGioHangDAO.findByGioHang_MaGioHang(gioHang.getMaGioHang());
        if (list == null) list = new ArrayList<>();

        ChiTietGioHang chiTiet = list.stream()
                .filter(c -> c.getSanPham().getMaSanPham().equals(sanPham.getMaSanPham()))
                .findFirst()
                .orElse(null);

        if (chiTiet == null) {
            chiTiet = new ChiTietGioHang();
            chiTiet.setGioHang(gioHang);
            chiTiet.setSanPham(sanPham);
            chiTiet.setSoLuong(soLuong);
        } else {
            chiTiet.setSoLuong(chiTiet.getSoLuong() + soLuong);
        }

        chiTietGioHangDAO.save(chiTiet);
    }

    // Xoá sản phẩm khỏi giỏ
    public void removeSanPhamFromGioHang(Integer maChiTietGioHang) {
        chiTietGioHangDAO.deleteById(maChiTietGioHang);
    }

    // Lấy toàn bộ chi tiết giỏ hàng
    public List<ChiTietGioHang> getChiTietByGioHang(GioHang gioHang) {
        return chiTietGioHangDAO.findByGioHang_MaGioHang(gioHang.getMaGioHang());
    }
}
