package com.unlimited.estimaciones.controller.thymeleaf;

import com.unlimited.estimaciones.config.LoggerColor;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.dto.EstimacionResponse;
import com.unlimited.estimaciones.service.EstimacionService;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/principal")
public class EstimacionThymeleafController {
    private final LoggerColor log = new LoggerColor(LoggerFactory.getLogger(getClass()));
    private final EstimacionService estimacionService;

    public EstimacionThymeleafController(EstimacionService estimacionService) {
        this.estimacionService = estimacionService;
    }


    @GetMapping("/estimaciones")
    public ModelAndView obtenerEstimaciones(
            @PageableDefault(size = 15,sort = "id",direction  = Sort.Direction.DESC) Pageable page,
            @RequestParam(value = "message",required = false) String message,
            @RequestParam(required = false) String busqueda,
            Model model
    ){
        log.infoRed("/principal/estimaciones");
        ModelAndView mav = new ModelAndView("listado");
        Page<EstimacionResponse> estimaciones;
        if(isNull(busqueda)){
            estimaciones=estimacionService.findAll(page);
        }else{
            estimaciones=estimacionService.findAll(busqueda,page);
        }


        model.addAttribute("listado",estimaciones);
        return mav;
    }

//    @GetMapping("/estimaciones")
//    public ModelAndView obtenerEstimaciones(
//            @RequestParam(value = "message",required = false) String message
//    ){
//        log.infoRed("/principal/estimaciones");
//        ModelAndView mav = new ModelAndView("estimaciones");
//        List<Estimacion> estimaciones = estimacionService.findAll();
//        mav.addObject("listado",estimaciones);
//        return mav;
//    }


    @GetMapping("/editarEstimacion")
    public ModelAndView obtenerPaginaEditarEstimacion(
            RedirectAttributes attributes,
            @RequestParam int id
    ){
//        /principal/editarEstimacion

        ModelAndView mav = new ModelAndView("editarEstimacion");
        Estimacion estimacion = estimacionService.findById(id);
        mav.addObject("estimacion",estimacion);
        return mav;
    }


    @GetMapping("/crearEstimacion")
    public ModelAndView crearEstimaciones(
            @RequestParam(value = "message",required = false) String message,
            HttpServletRequest request
    ){
        log.infoRed("/principal/crearEstimacion");
        ModelAndView mav = new ModelAndView("editarEstimacion");
        mav.addObject("estimacion",estimacionService.saveEstimacion(new Estimacion()));
        return mav;
    }


    @PostMapping("/crearEstimacion")
    public ModelAndView saveEstimacion(
            @ModelAttribute Estimacion estimacion,
            HttpServletRequest request,
            RedirectAttributes redirectAttrs
    ){
        ModelAndView mav = new ModelAndView("redirect:/principal/estimaciones");
        estimacionService.saveEstimacion(estimacion);
        return mav;
    }


//    private Estimacion obtenerEstimacion(HttpServletRequest request){
//        Estimacion estimacion = (Estimacion) request.getSession().getAttribute("estimacion");
//        if(estimacion==null){
//            return new Estimacion();
//        }
//        return estimacion;
//    }
//
//    private  void guardarEstimacion(Estimacion estimacion,HttpServletRequest request ){
//        request.getSession().setAttribute("estimacion",estimacion);
//    }
}
