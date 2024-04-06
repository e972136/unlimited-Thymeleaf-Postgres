package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.RepuestoRequest;
import com.unlimited.estimaciones.service.EstimacionService;
import com.unlimited.estimaciones.service.RepuestoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/repuestos")
public class RepuestosThymeleafController {

    private final EstimacionService estimacionService;
    private final RepuestoService repuestoService;

    public RepuestosThymeleafController(EstimacionService estimacionService, RepuestoService repuestoService) {
        this.estimacionService = estimacionService;
        this.repuestoService = repuestoService;
    }

    @GetMapping("/editarRepuestos")
    public String obtenerPaginaEditarRepuesto(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ){
        Estimacion estimacion = estimacionService.findById(id);
        model.addAttribute("estimacion",estimacion);
        model.addAttribute("repuestoRequest",new RepuestoRequest(id,0,"", BigDecimal.ZERO));
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

    @PostMapping("/saveRepuestosEntity")
    public String guardarRepuestoEntidad(
            @ModelAttribute RepuestoRequest repuestoRequest,
            @RequestParam Integer idEstimacion,
            Model model
    ){
//        /principal/saveRepuesto
        System.err.println("patito "+idEstimacion);
        System.err.println("repuestoRequest "+repuestoRequest);
        RepuestoRequest request = repuestoService.saveRepuesto(repuestoRequest);
   //     estimacion= estimacionService.saveRepuestos(estimacion);
//        model.addAttribute("estimacion",estimacion);
        return "redirect:/repuestos/editarRepuestos/?id="+idEstimacion;
    }

    @GetMapping("eliminarRepuesto")
    public String eliminarRepuesto(
            @RequestParam Integer idEstimacion,
            @RequestParam int idRepuesto,
            Model model
    ){

        int estimacion = repuestoService.eliminarRepuesto(idRepuesto);
        return "redirect:/repuestos/editarRepuestos?id="+idEstimacion;
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
