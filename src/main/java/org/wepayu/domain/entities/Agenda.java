package org.wepayu.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wepayu.domain.entities.enums.Pagamento;
@Entity
@Getter
@Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private Pagamento tipo;
    private float valor;



}