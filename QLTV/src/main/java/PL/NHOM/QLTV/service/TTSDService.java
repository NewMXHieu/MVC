package PL.NHOM.QLTV.service;

import PL.NHOM.QLTV.entity.ThietBi;
import org.springframework.data.domain.Page;

public interface TTSDService {
    Page<ThietBi> listAll(int PageNum);
}
