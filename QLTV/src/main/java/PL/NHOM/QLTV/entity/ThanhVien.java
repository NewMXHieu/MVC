package PL.NHOM.QLTV.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "thanhvien")
public class ThanhVien {
    @Id
    @Column(name = "MaTV", unique = true)
    @NotNull(message = "Không được để trống Id")
    private Integer maTV;

    @NotBlank(message = "Không được để trống Tên")
    @NotNull
    @Column(name = "Ten")
    private String ten;

    @NotBlank(message = "Không được để trống Khoa")
    @Column(name = "Khoa")
    private String khoa;

    @NotBlank(message = "Không được để trống Nganh")
    @Column(name = "Nganh")
    private String nganh;

    @NotBlank(message = "Không được để trống sdt")
    @Column(name = "SDT")
    private String sdt;

    @NotBlank(message = "Không được để trống password")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Không được để trống email")
    @Email(message = "Không đúng định dạng")
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thanhVien")
    private List<ThongTinSD> listTTSD;
}
