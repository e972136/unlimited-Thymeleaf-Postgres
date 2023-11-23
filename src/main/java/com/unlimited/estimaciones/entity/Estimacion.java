package com.unlimited.estimaciones.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estimacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "estimacionParent")
    @JsonManagedReference
    private List<Reparacion> reparaciones;

    @OneToMany(mappedBy = "estimacionParent")
    @JsonManagedReference
    private List<ReparacionAdicional> reparacionesAdicionales;

    @OneToMany(mappedBy = "estimacionParent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Repuesto> repuestos;

    @Transient
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

    @Transient
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

    @Transient
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

    public Estimacion(int id) {
        this.id = id;
    }
}
