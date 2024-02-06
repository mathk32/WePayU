package org.wepayu.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "Corrente")
    private String conta_corrente;
    private String agencia;

    public Banco(String conta_corrente, String agencia) {
        this.conta_corrente = conta_corrente;
        this.agencia = agencia;
    }
}
