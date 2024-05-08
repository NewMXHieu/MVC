package edu.poly.stringweb1.service;

import edu.poly.stringweb1.entity.ThietBi;
import org.springframework.data.domain.Page;

public interface TTSDService {
    Page<ThietBi> listAll(int PageNum);
}
