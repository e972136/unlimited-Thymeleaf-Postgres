package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.repository.EstimacionRepository;
import com.unlimited.estimaciones.repository.ReparacionRepository;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstimacionServiceImpl implements EstimacionService {
    private final EstimacionRepository estimacionRepository;
    private final ReparacionRepository reparacionRepository;

    public EstimacionServiceImpl(EstimacionRepository estimacionRepository, ReparacionRepository reparacionRepository) {
        this.estimacionRepository = estimacionRepository;
        this.reparacionRepository = reparacionRepository;
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

    @Override
    @Transactional
    public Estimacion saveRepuestos(Estimacion estimacion) {
        Estimacion estimacionDB = estimacionRepository.findById(estimacion.getId()).get();
        List<Repuesto> repuestos = estimacion.getRepuestos();
        estimacionDB.getRepuestos().forEach(r->{
            Repuesto repuesto = repuestos.stream().filter(a -> a.getId() == r.getId()).findAny().get();
            r.setDescripcion(repuesto.getDescripcion());
            r.setPrecio(repuesto.getPrecio());
        });
        return estimacionDB;
    }

    @Override
    public Estimacion saveReparaciones(Estimacion estimacion) {
        Estimacion estimacionDB = estimacionRepository.findById(estimacion.getId()).get();

        reparacionRepository.deleteAllById(estimacionDB.getReparaciones().stream().map(x->x.getId()).collect(Collectors.toList()));

//        estimacionDB.getReparaciones().forEach(x->
//                reparacionRepository.deleteById(x.getId())
//        );

        Estimacion x = new Estimacion();
        x.setId(estimacionDB.getId());
        estimacion.getReparaciones().forEach(t->{
            t.setEstimacionParent(x);
            reparacionRepository.save(t);
        });


//        reparacionRepository.saveAll(estimacion.getReparaciones());
/*
        List<Reparacion> reparaciones = estimacion.getReparaciones();

        estimacionDB.getReparaciones().forEach(r->{
            Reparacion repuesto = reparaciones.stream().filter(a -> a.getId() == r.getId()).findAny().get();
            r.setDetalleReparacion(repuesto.getDetalleReparacion());
            r.setPrecio(repuesto.getPrecio());
        });

 */
        return estimacionDB;
    }


}
