package com.unlimited.estimaciones.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name="estimacion_id", nullable=false)
    @JsonBackReference
    Estimacion estimacionParent;

    String detalleReparacion;
    BigDecimal precio;
}
