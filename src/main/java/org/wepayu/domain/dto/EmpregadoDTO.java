package org.wepayu.domain.dto;

public record EmpregadoDTO(String nome, String endereco, String contrato, Integer sindicato_id,
                           Integer pagamento_id) {

    public EmpregadoDTO(String nome, String endereco, String contrato) {
        this(nome, endereco, contrato, null, null);
    }
}



