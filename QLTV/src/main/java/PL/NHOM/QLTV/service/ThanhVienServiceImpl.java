package PL.NHOM.QLTV.service;

import PL.NHOM.QLTV.entity.ThanhVien;
import PL.NHOM.QLTV.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhVienServiceImpl implements ThanhVienService {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Override
    public Page<ThanhVien> listAll(int pageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return thanhVienRepository.findAll(pageable);
    }

    @Override
    public List<ThanhVien> searchList(String keyword) {
        return thanhVienRepository.findByTenContaining(keyword);
    }

    @Override
    public void SaveThanhVien(ThanhVien tv) {
        thanhVienRepository.save(tv);
    }

    @Override
    public void DelteThanhVien(Integer id) {
        thanhVienRepository.deleteById(id);
    }

    @Override
    public ThanhVien getByMaTV(Integer id) {
        return thanhVienRepository.getByMaTV(id);
    }

    @Override
    public boolean existsByMaTVAndPassword(int id, String password) {
        return thanhVienRepository.existsByMaTVAndPassword(id, password);
    }

}
