package edu.poly.stringweb1.repository;

import edu.poly.stringweb1.entity.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    Boolean existsByMaTVAndPassword(Integer MaTV, String password);

    ThanhVien getByMaTV(Integer id);

    List<ThanhVien> findByTenContaining(String keyword);
}
