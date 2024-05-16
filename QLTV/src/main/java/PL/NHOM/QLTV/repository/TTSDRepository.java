package PL.NHOM.QLTV.repository;

import PL.NHOM.QLTV.entity.ThietBi;
import PL.NHOM.QLTV.entity.ThongTinSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TTSDRepository extends JpaRepository<ThongTinSD, Integer> {
    @Query("select distinct tb from ThongTinSD ttsd right outer join ttsd.thietBi tb where ttsd.tgDatCho is null")
    Page<ThietBi> getAll(Pageable pageable);
}
