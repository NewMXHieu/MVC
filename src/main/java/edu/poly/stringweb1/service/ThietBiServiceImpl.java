package edu.poly.stringweb1.service;

import edu.poly.stringweb1.entity.ThietBi;
import edu.poly.stringweb1.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThietBiServiceImpl implements ThietBiService{

    @Autowired
    private ThietBiRepository thietBiRepository;
    @Override
    public Page<ThietBi> listAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return thietBiRepository.findAll(pageable);
    }

    @Override
    public List<ThietBi> getAllSearch(String keyword) {
        return thietBiRepository.findByTenTBContaining(keyword);
    }

}
