package edu.poly.stringweb1.repository;

import edu.poly.stringweb1.entity.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {

    List<ThietBi> findByTenTBContaining(String keyword);
}
