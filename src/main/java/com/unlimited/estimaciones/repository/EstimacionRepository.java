package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Estimacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimacionRepository extends JpaRepository<Estimacion,Integer> {

    List<Estimacion> findAllByOrderByIdDesc();

    Page<Estimacion> findAllByPlacaContainingIgnoreCaseOrAseguradoContainingIgnoreCaseOrId(String busqueda, String busqueda1, Integer s, Pageable page);
}
