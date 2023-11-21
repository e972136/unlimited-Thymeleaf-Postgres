package com.unlimited.estimaciones.controller;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstimacionController {
    private final EstimacionService estimacionService;

    public EstimacionController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }


    @GetMapping("/{estimacion}")
    Estimacion obtenerEstimacion(
            @PathVariable int estimacion
    ){
        Estimacion byId = estimacionService.findById(estimacion);
        return byId;
    }

    @GetMapping("/estimaciones")
    List<Estimacion> obtenerEstimaciones(

    ){
        return estimacionService.findAll();
    }
}
