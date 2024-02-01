package org.wepayu.domain.entities;

import jakarta.persistence.*;
import org.wepayu.domain.entities.enums.Pagamento;
@Entity

public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private Pagamento tipo;
    private float valor;



}