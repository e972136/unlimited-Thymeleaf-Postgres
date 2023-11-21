package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.entity.Estimacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstimacionService {
    Page<Estimacion> findAll(Pageable page);
    Estimacion findById(int id);

    List<Estimacion> findAll();

    Estimacion saveRepuestos(Estimacion estimacion);

    Estimacion saveReparaciones(Estimacion estimacion);
}
