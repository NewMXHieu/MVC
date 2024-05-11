package PL.NHOM.QLTV.repository;

import PL.NHOM.QLTV.entity.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {

    List<ThietBi> findByTenTBContaining(String keyword);
}
//package PL.NHOM.QLTV.repository;
//
//import PL.NHOM.QLTV.entity.ThietBi;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {
//
//    List<ThietBi> findByTenTBContaining(String keyword);
//}