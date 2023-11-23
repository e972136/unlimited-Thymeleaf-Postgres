package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/principal")
public class EstimacionThymeleafController {
    private final EstimacionService estimacionService;

    public EstimacionThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }


    @GetMapping("/estimaciones")
    public String obtenerEstimaciones(
            @PageableDefault(size = 15,sort = "id",direction  = Sort.Direction.DESC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda,
            Model model
    ){
        System.err.println("/principal/estimaciones");
        Page<Estimacion> estimaciones=estimacionService.findAll(page);

        model.addAttribute("listado",estimaciones);
        return "listado";
    }


    @GetMapping("/editarEstimacion")
    public String obtenerPaginaEditarEstimacion(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ){
//        /principal/editarEstimacion
        Estimacion estimacion = estimacionService.findById(id);
        model.addAttribute("estimacion",estimacion);
        return "editarEstimacion";
    }




}
