package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.service.EstimacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/repuestos")
public class RepuestosThymeleafController {

    private final EstimacionService estimacionService;

    public RepuestosThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }

    @GetMapping("/editarRepuestos")
    public String obtenerPaginaEditarRepuesto(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        Estimacion estimacion = estimacionService.findById(id);
        model.addAttribute("estimacion",estimacion);
        return "editarRepuestos";
    }

    @PostMapping("/saveRepuestos")
    public String guardarRepuesto(
            @ModelAttribute Estimacion estimacion, Model model
    ){
//        /principal/saveRepuesto
        System.err.println("patito "+estimacion);
        estimacion= estimacionService.saveRepuestos(estimacion);
        model.addAttribute("estimacion",estimacion);
        return "redirect:/principal/editarEstimacion/?id="+estimacion.getId();
    }

    @GetMapping("agregarRepuesto")
    public String agregarRepuesto(
            @RequestParam int id,
            Model model
    ){
        estimacionService.agregarRepuesto(id);
        //llamar al endpoint
        return "redirect:/repuestos/editarRepuestos?id="+id;
    }
}
