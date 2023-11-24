package com.unlimited.estimaciones.entity.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unlimited.estimaciones.entity.Estimacion;
import com.unlimited.estimaciones.entity.Reparacion;
import com.unlimited.estimaciones.entity.ReparacionAdicional;
import com.unlimited.estimaciones.entity.Repuesto;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@Builder
public class EstimacionResponse {
    int id;

    String asegurado;
    String estimadoPor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date fechaEvaluacion;

    String aseguradora;
    String placa;
    String marca;
    String modelo;
    String kM;
    String colorVehiculo;
    String anioVehiculo;
    String vinOSerie;
    BigDecimal totalCostosManoObra;
    BigDecimal totalCostosAdicionalesMateriales;
    BigDecimal costoManoDeObraGerente;
    BigDecimal costoMaterialesGerente;
    String obs;
    String implementadoPor;
    String nFechaIngreso;
    String nEstadoVehiculo;
    String nFechaEntrega;
    String nHoraEntrega;
    String nPintor;
    String nAjustadorAseguradora;
    BigDecimal nCostoMaterialGastado;

    private List<Reparacion> reparaciones;

    private List<ReparacionAdicional> reparacionesAdicionales;

    private List<Repuesto> repuestos;


    BigDecimal totalRepuestos;
    public BigDecimal getTotalRepuestos(){
        if(isNull(repuestos) || repuestos.isEmpty()){
            return BigDecimal.ZERO;
        }
        return repuestos
                .stream()
                .map(a -> (a.getPrecio()==null)?BigDecimal.ZERO:a.getPrecio())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    BigDecimal totalReparaciones;
    public BigDecimal getTotalReparaciones(){
        if(isNull(reparaciones) || reparaciones.isEmpty()){
            return BigDecimal.ZERO;
        }

        return reparaciones
                .stream()
                .map(a -> (a.getPrecio()==null)?BigDecimal.ZERO:a.getPrecio())
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    BigDecimal totalReparacionesAdicionales;
    public BigDecimal getTotalReparacionesAdicionales(){
        if(isNull(reparacionesAdicionales) || reparacionesAdicionales.isEmpty()){
            return BigDecimal.ZERO;
        }
        return reparacionesAdicionales
                .stream()
                .map(a -> (a.getValorReparacion()==null?BigDecimal.ZERO:a.getValorReparacion()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
    public EstimacionResponse(int id) {
        this.id = id;
    }

    public EstimacionResponse fromEntity(Estimacion estimacion){
        return null;
    }
}
