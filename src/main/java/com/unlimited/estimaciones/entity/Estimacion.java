package com.unlimited.estimaciones.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @JsonFormat(pattern="yyyy-MM-dd")
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
    private List<ReparacionAdicional> ReparacionesAdicionales;

    @OneToMany(mappedBy = "estimacionParent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Repuesto> repuestos;
}
