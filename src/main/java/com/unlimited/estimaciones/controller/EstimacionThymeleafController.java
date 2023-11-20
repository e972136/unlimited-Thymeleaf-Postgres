package com.unlimited.estimaciones.controller;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/principal")
public class EstimacionThymeleafController {
    private final EstimacionService estimacionService;

    public EstimacionThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }


    @GetMapping("/estimaciones")
    public String obtenerEstimaciones(
            @PageableDefault(size = 5,sort = "id",direction  = Sort.Direction.DESC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda,
            Model model
    ){
        Page<Estimacion> estimaciones=estimacionService.findAll(page);

        model.addAttribute("listado",estimaciones);
        return "listado";
    }
}
