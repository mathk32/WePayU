package org.wepayu.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Sindicato")
public class Sindicato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private Float taxa_mensal;
    private Float taxa_servico;


}