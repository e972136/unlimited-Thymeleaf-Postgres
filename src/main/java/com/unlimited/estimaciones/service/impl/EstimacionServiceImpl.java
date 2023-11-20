package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.repository.EstimacionRepository;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimacionServiceImpl implements EstimacionService {
    private final EstimacionRepository estimacionRepository;

    public EstimacionServiceImpl(EstimacionRepository estimacionRepository) {
        this.estimacionRepository = estimacionRepository;
    }

    @Override
    public Page<Estimacion> findAll(Pageable page) {
        return estimacionRepository.findAll(page);
    }

    @Override
    public Estimacion findById(int id) {
        return estimacionRepository.findById(id).get();
    }

    @Override
    public List<Estimacion> findAll() {
        return estimacionRepository.findAll();
    }
}
