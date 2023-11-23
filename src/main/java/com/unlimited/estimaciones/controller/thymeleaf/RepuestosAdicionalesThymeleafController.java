package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reparacionesAdicionales")
public class RepuestosAdicionalesThymeleafController {

    private final EstimacionService estimacionService;

    public RepuestosAdicionalesThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }

    @GetMapping("/editar")
    public String editar(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        Estimacion estimacion = estimacionService.findById(id);
        model.addAttribute("estimacion",estimacion);
        return "editarReparacionesAdicionales";
    }

    @PostMapping("/saveReparacionesAdicionales")
    public String saveReparacionesAdicionales(
            @ModelAttribute Estimacion estimacion, Model model
    ){
        System.err.println("/principal/saveReparacionesAdicionales/"+estimacion);
        estimacion = estimacionService.saveReparacionesAdicionales(estimacion);
        model.addAttribute("estimacion",estimacion);
        return "redirect:/principal/editarEstimacion/?id="+estimacion.getId();
    }

    @GetMapping("agregarReparacionesAdicionales")
    public String agregarReparacionesAdicionales(
            @RequestParam int id,
            Model model
    ){
        estimacionService.agregarReparacionAdicional(id);
        //llamar al endpoint
        return "redirect:/reparacionesAdicionales/editar?id="+id;
    }
}
