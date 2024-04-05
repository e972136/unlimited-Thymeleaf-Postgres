package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import com.unlimited.estimaciones.service.RepuestoService;

public class RepuestoServiceImpl implements RepuestoService {

    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public RepuestoRequest saveRepuesto(RepuestoRequest repuestoRequest) {
        Estimacion x = new Estimacion();
        x.setId(repuestoRequest.idEstimacion());
        Repuesto repuesto= new Repuesto();
        repuesto.setEstimacionParent(x);
        repuesto.setDescripcion(repuestoRequest.descripcion());
        repuesto.setPrecio(repuestoRequest.precio());
        /*
             Estimacion estimacionParent;

    String descripcion;
    BigDecimal precio;
        * */

        repuestoRepository.save(repuesto);
        return null;
    }
}
