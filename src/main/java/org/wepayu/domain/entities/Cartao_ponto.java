package org.wepayu.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Table(name = "CartaoPonto")
@Getter
@Setter
@NoArgsConstructor
public class Cartao_ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private LocalTime Entrada;

    private LocalTime Saida;

    private LocalDate dia;
    @ManyToOne
    @JoinColumn(name = "empregado_id")
    private Empregado empregado;



    public Cartao_ponto(LocalTime Entrada, LocalTime Saida, Empregado empregado, LocalDate dia){
        this.Entrada = Entrada;
        this.Saida = Saida;
        this.empregado = empregado;
        this.dia = dia;

    }

}