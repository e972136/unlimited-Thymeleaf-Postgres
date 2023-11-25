package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.config.LoggerColor;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.service.EstimacionService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reparaciones")
public class ReparacionesThymeleafController {
    private final LoggerColor log = new LoggerColor(LoggerFactory.getLogger(getClass()));
    private final EstimacionService estimacionService;

    public ReparacionesThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }

    @GetMapping("/editarReparaciones")
    public String obtenerPaginaEditarReparaciones(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        Estimacion estimacion = estimacionService.findById(id);
        model.addAttribute("estimacion",estimacion);
        return "editarReparaciones";
    }

    @PostMapping("/saveReparaciones")
    public String guardarReparaciones(
            @ModelAttribute Estimacion estimacion, Model model
    ){
        log.infoRed("/principal/saveReparaciones/"+estimacion);
        estimacion= estimacionService.saveReparaciones(estimacion);
        model.addAttribute("estimacion",estimacion);
        //llamar al endpoint
        return "redirect:/principal/editarEstimacion/?id="+estimacion.getId();
    }

    @GetMapping("/agregarReparacion")
    public String agregarReparacion(
            @RequestParam int id,
            Model model
    ){
        estimacionService.agregarReparacion(id);
        //llamar al endpoint
        return "redirect:/reparaciones/editarReparaciones?id="+id;
    }
}
