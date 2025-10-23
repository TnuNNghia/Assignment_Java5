package poly.edu.asm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poly.edu.asm.dao.*;
import poly.edu.asm.entity.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonHangService {

    private final DonHangDAO donHangDAO;
    private final ChiTietDonHangDAO chiTietDonHangDAO;
    private final ChiTietGioHangDAO chiTietGioHangDAO;
    private final GioHangDAO gioHangDAO;

    // ✅ Trả về đơn hàng vừa tạo
    public DonHang taoDonHangTuGioHang(KhachHang khachHang) {
        GioHang gioHang = gioHangDAO.findByKhachHang_MaKhachHang(khachHang.getMaKhachHang());
        if (gioHang == null) return null;

        List<ChiTietGioHang> chiTietGioHangs = chiTietGioHangDAO.findByGioHang_MaGioHang(gioHang.getMaGioHang());
        if (chiTietGioHangs.isEmpty()) return null;

        // 1️⃣ Tạo đơn hàng mới
        DonHang donHang = new DonHang();
        donHang.setKhachHang(khachHang);
        donHang.setNgayDatHang(LocalDate.now());
        donHangDAO.save(donHang);

        // 2️⃣ Tạo chi tiết đơn hàng
        for (ChiTietGioHang ctgh : chiTietGioHangs) {
            ChiTietDonHang ctdh = new ChiTietDonHang();
            ctdh.setDonHang(donHang);
            ctdh.setSanPham(ctgh.getSanPham());
            ctdh.setSoLuong(ctgh.getSoLuong());
            ctdh.setDonGia(ctgh.getSanPham().getDonGia());
            chiTietDonHangDAO.save(ctdh);
        }

        // 3️⃣ Xóa giỏ hàng sau khi mua
        chiTietGioHangDAO.deleteAll(chiTietGioHangs);

        // 4️⃣ Nạp lại chi tiết để hiển thị
        donHang.setChiTietDonHangs(chiTietDonHangDAO.findByDonHang_MaDonHang(donHang.getMaDonHang()));
        return donHang;
    }

    public List<DonHang> getDonHangByKhachHang(KhachHang khachHang) {
        return donHangDAO.findByKhachHang_MaKhachHang(khachHang.getMaKhachHang());
    }
}
