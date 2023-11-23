package com.unlimited.estimaciones.service.impl;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.ReparacionAdicional;
import com.unlimited.estimaciones.entity.Repuesto;
import com.unlimited.estimaciones.repository.EstimacionRepository;
import com.unlimited.estimaciones.repository.ReparacionAdicionalRepository;
import com.unlimited.estimaciones.repository.ReparacionRepository;
import com.unlimited.estimaciones.repository.RepuestoRepository;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class EstimacionServiceImpl implements EstimacionService {
    private final EstimacionRepository estimacionRepository;
    private final ReparacionRepository reparacionRepository;

    private final RepuestoRepository repuestoRepository;

    private final ReparacionAdicionalRepository reparacionAdicionalRepository;

    public EstimacionServiceImpl(EstimacionRepository estimacionRepository, ReparacionRepository reparacionRepository, RepuestoRepository repuestoRepository, ReparacionAdicionalRepository reparacionAdicionalRepository) {
        this.estimacionRepository = estimacionRepository;
        this.reparacionRepository = reparacionRepository;
        this.repuestoRepository = repuestoRepository;
        this.reparacionAdicionalRepository = reparacionAdicionalRepository;
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

//        reparacionRepository.deleteAllById(estimacionDB.getReparaciones().stream().map(x->x.getId()).collect(Collectors.toList()));
//        estimacionDB.getReparaciones().forEach(x->
//                reparacionRepository.deleteById(x.getId())
//        );

//        Actualiza o ingresa datos nuevos
        Estimacion x = new Estimacion();
        List<Reparacion> reparacionesPantalla = estimacion.getReparaciones();
        if(!isNull(reparacionesPantalla) && !reparacionesPantalla.isEmpty()){
            x.setId(estimacionDB.getId());
            reparacionesPantalla.forEach(t->{
                t.setEstimacionParent(x);
            });
            reparacionRepository.saveAll(reparacionesPantalla);
        }
//      Elimina los que no deben estar
        return estimacionDB;
    }

    @Override
    public Estimacion saveReparacionesAdicionales(Estimacion estimacion) {
        List<ReparacionAdicional> reparacionesAdicionalesPantalla = estimacion.getReparacionesAdicionales();
        if(!isNull(reparacionesAdicionalesPantalla) && !reparacionesAdicionalesPantalla.isEmpty()){
            Estimacion x = new Estimacion(estimacion.getId());
            reparacionesAdicionalesPantalla.forEach(t->{
                t.setEstimacionParent(x);
            });
            reparacionAdicionalRepository.saveAll(reparacionesAdicionalesPantalla);
        }
        return estimacion;
    }

    @Override
    public void agregarReparacion(int id) {
        System.out.println("patito "+id);
        Estimacion x = new Estimacion();
        x.setId(id);
        reparacionRepository.saveAndFlush(new Reparacion(0,x," ", BigDecimal.ZERO));
    }

    @Override
    public void agregarRepuesto(int id) {
        repuestoRepository.saveAndFlush(new Repuesto(0,new Estimacion(id)," ",BigDecimal.ZERO));
    }

    @Override
    public void agregarReparacionAdicional(int id) {
        reparacionAdicionalRepository.saveAndFlush(
          new ReparacionAdicional(
                  null,
                  new Estimacion(id),
                  " ",
                  BigDecimal.ZERO,
                  " "
          )
        );
    }
/*

 */
}
