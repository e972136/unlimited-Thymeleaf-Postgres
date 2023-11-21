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
    int id;

    @ManyToOne
    @JoinColumn(name="estimacionId", nullable=false)
    @JsonBackReference
    Estimacion estimacionParent;

    String reparacionAdicional;
    BigDecimal valorReparacion;
    String tipo;
}
