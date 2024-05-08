package edu.poly.stringweb1.service;

import edu.poly.stringweb1.entity.ThietBi;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ThietBiService {
    Page<ThietBi> listAll(int pageNum);

    List<ThietBi> getAllSearch(String keyword);
}
