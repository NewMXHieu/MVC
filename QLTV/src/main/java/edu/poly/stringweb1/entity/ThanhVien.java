package edu.poly.stringweb1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table(name = "thanhvien")
public class ThanhVien {
    @Id
    @Column(name = "MaTV")
    private int maTV;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "Khoa")
    private String khoa;

    @Column(name = "Nganh")
    private String nganh;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thanhVien")
    private List<ThongTinSD> thongTinSD;

    // Default constructor
    public ThanhVien() {
    }

    // Parameterized constructor
    public ThanhVien(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.khoa = khoa;
        this.nganh = nganh;
        this.sdt = sdt;
        this.password = password;
        this.email = email;
    }
}

