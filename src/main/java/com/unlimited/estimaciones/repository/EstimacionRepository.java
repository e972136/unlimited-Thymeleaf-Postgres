package com.unlimited.estimaciones.repository;

import com.unlimited.estimaciones.entity.Estimacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimacionRepository extends JpaRepository<Estimacion,Integer> {
}
