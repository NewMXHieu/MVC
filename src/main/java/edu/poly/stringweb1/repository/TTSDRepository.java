package edu.poly.stringweb1.repository;

import edu.poly.stringweb1.entity.ThietBi;
import edu.poly.stringweb1.entity.ThongTinSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TTSDRepository extends JpaRepository<ThongTinSD, Integer> {
    @Query("select distinct tb from ThongTinSD ttsd right outer join ttsd.thietBi tb where ttsd.tgDatCho is null")
    Page<ThietBi> getAll(Pageable pageable);
}
