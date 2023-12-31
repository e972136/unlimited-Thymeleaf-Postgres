package com.unlimited.estimaciones.service;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.EstimacionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstimacionService {
    Page<EstimacionResponse> findAll(Pageable page);
    Estimacion findById(int id);

    List<Estimacion> findAll();

    Estimacion saveRepuestos(Estimacion estimacion);

    Estimacion saveReparaciones(Estimacion estimacion);

    Estimacion saveReparacionesAdicionales(Estimacion estimacion);

    void agregarReparacion(int id);

    void agregarRepuesto(int id);

    void agregarReparacionAdicional(int id);


    Estimacion saveEstimacion(Estimacion estimacion);

    Page<EstimacionResponse> findAll(String busqueda, Pageable page);
}
