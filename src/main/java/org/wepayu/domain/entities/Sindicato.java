package org.wepayu.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sindicato")
@Getter
public class Sindicato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private Float taxa_mensal;
    private Float taxa_servico;


}