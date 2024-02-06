package org.wepayu.domain.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Sindicato")
@Getter
@NoArgsConstructor
public class Sindicato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private Float taxa_mensal;
    private Float taxa_servico;


    public Sindicato(Float taxa_mensal, Float taxa_servico){
        this.taxa_mensal = taxa_mensal;
        this.taxa_servico = taxa_servico;

    }

}