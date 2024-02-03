package org.wepayu.domain.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.wepayu.domain.entities.enums.Contrato;

@Entity
@Table(name = "Empregados")
@Getter
@Setter
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

    @Builder()
    public Empregado(Integer id, String nome, String endereco, String tipo, Sindicato sindicato, Agenda pagamento){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = Contrato.valueOf(tipo);
        this.sindicato = sindicato;
        this.pagamento = pagamento;

    }
    public Empregado(){}

}
