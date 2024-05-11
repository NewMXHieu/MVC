package PL.NHOM.QLTV.service;

import PL.NHOM.QLTV.entity.ThietBi;
import PL.NHOM.QLTV.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThietBiServiceImpl implements ThietBiService{

    @Autowired
    private ThietBiRepository ThietBiRepository;
    @Override
    public Page<ThietBi> listAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return ThietBiRepository.findAll(pageable);
    }

    @Override
    public List<ThietBi> getAllSearch(String keyword) {
        return ThietBiRepository.findByTenTBContaining(keyword);
    }

}
