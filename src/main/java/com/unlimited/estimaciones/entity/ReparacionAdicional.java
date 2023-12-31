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
public class ReparacionAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name="estimacionId", nullable=false)
    @JsonBackReference
    Estimacion estimacionParent;

    @Column(name = "reparacionAdicional")
    String reparacionAdicionalDetalle;

    BigDecimal valorReparacion;
    String tipo;
}
