package org.wepayu.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Entity
@Table(name = "CartaoPonto")
@Getter
@Setter
public class Cartao_ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private LocalTime Entrada;

    private LocalTime Saida;
    @OneToOne
    @JoinColumn(name = "empregado_id")
    private Empregado empregado;

}