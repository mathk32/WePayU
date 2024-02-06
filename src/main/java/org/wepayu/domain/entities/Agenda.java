package org.wepayu.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wepayu.domain.entities.enums.Pagamento;
import org.wepayu.domain.entities.enums.TipoPagemento;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private Pagamento tipo;
    @Enumerated(EnumType.STRING)
    private TipoPagemento metodo;
    private float valor;
    private LocalDate dia;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    Banco banco;

    public Agenda(String tipo_pagamento, float valor, LocalDate dia, String metodo){
        this.tipo = Pagamento.valueOf(tipo_pagamento);
        this.valor = valor;
        this.dia = dia;
        this.metodo = TipoPagemento.valueOf(metodo);
    }

    public Agenda(Pagamento tipo, TipoPagemento metodo, float valor, LocalDate dia) {
        this.tipo = tipo;
        this.metodo = metodo;
        this.valor = valor;
        this.dia = dia;
    }
}