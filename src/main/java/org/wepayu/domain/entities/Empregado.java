package org.wepayu.domain.entities;

import jakarta.persistence.*;
import org.wepayu.domain.entities.enums.Contrato;

@Entity
@Table(name = "Empregados")
public class Empregado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String endereco;
    @Column(name = "contrato")
    @Enumerated(EnumType.STRING)
    private Contrato tipo;
    @ManyToOne
    @JoinColumn(name = "sindicato_id")
    private Sindicato sindicato;

    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    private Agenda pagamento;

    public Empregado(String nome, String tipo){
        this.nome = nome;
        this.tipo = Contrato.valueOf(tipo);

    }

}
