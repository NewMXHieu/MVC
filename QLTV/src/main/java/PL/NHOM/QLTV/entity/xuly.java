package PL.NHOM.QLTV.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "xuly")

public class xuly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaXL;
    @Column(nullable = false, length = 10)
    private int MaTV;

    @Column(nullable = false, length = 250)
    private String HinhThucXL;

    @Column(nullable = false, length = 100)
    private Integer  SoTien;

    @Column
    private Date NgayXL;

    @Column(nullable = false, length = 2)
    private int TrangThaiXL;

    public int getMaXL() {
        return MaXL;
    }

    public void setMaXL(int maXL) {
        MaXL = maXL;
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int maTV) {
        MaTV = maTV;
    }

    public String getHinhThucXL() {
        return HinhThucXL;
    }

    public void setHinhThucXL(String hinhThucXL) {
        HinhThucXL = hinhThucXL;
    }

    public Integer getSoTien() {
        return SoTien;
    }

    public void setSoTien(Integer soTien) {
        SoTien = soTien;
    }

    public Date getNgayXL() {
        return NgayXL;
    }

    public void setNgayXL(Date ngayXL) {
        NgayXL = ngayXL;
    }

    public int getTrangThaiXL() {
        return TrangThaiXL;
    }

    public void setTrangThaiXL(int trangThaiXL) {
        TrangThaiXL = trangThaiXL;
    }
}
