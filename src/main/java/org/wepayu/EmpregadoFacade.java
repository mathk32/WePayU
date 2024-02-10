package org.wepayu;

import org.wepayu.controller.CartaoPontoController;
import org.wepayu.controller.EmpregadoController;
import org.wepayu.domain.dto.EmpregadoDTO;

import org.wepayu.domain.entities.Empregado;
import org.wepayu.domain.entities.enums.Contrato;


public class EmpregadoFacade {
    private final EmpregadoController empregadoController;
    private static CartaoPontoController cartaoPontoController;

    static {
        cartaoPontoController = new CartaoPontoController();
    }
    public EmpregadoFacade() {
        empregadoController = new EmpregadoController();
    }

    public Integer criarEmpregado(String nome, String endereco, String tipo, String salario) throws Exception {
        validarEmpregado(nome, endereco, salario);

        try {

            Contrato contrato = Contrato.valueOf(tipo.toLowerCase());


            if (!isTipoAplicavel(contrato)) {
                throw new Exception("Tipo nao aplicavel.");
            }

            EmpregadoDTO dto = new EmpregadoDTO(nome, endereco, contrato.name());
            return empregadoController.salvar_empregado(dto);
        } catch (IllegalArgumentException e) {

            throw new Exception("Tipo invalido.");
        }

    }
    private void validarEmpregado(String nome, String endereco, String salario) throws Exception {
        if (nome.isEmpty()) {
            throw new Exception("Nome nao pode ser nulo.");
        } else if (endereco.isEmpty()) {
            throw new Exception("Endereco nao pode ser nulo.");
        } else if (salario.isEmpty()) {
            throw new Exception("Salario nao pode ser nulo.");
        } else if (!isFloat(salario, "Salario")) {

        }
    }





    private boolean isFloat(String valor, String tipo) throws Exception {
        try {
            Float valorFloat = Float.parseFloat(valor);

            if (valorFloat < 0) {
                if(tipo == "Comissao"){
                    throw new Exception(tipo + " deve ser nao-negativa.");
                }
                throw new Exception(tipo + " deve ser nao-negativo.");
            }

            return true;
        } catch (NumberFormatException e) {
            if (tipo == "Comissao") {
                throw new Exception(tipo + " deve ser numerica.");
            }
            throw new Exception(tipo + " deve ser numerico.");
        }
    }




    private boolean isTipoAplicavel(Contrato contrato) {
        return true;
    }


    public void removerEmpregado(String id) throws Exception {
        if (id.isEmpty()) {
            throw new Exception("Identificacao do empregado nao pode ser nula.");
        }
        Integer id_int;
        try {
            id_int = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new Exception("Empregado nao existe.");
        }
        empregadoController.remover_id(id_int);
    }


    public Object getAtributoEmpregado(Integer empregadoId, String atributo) throws Exception {
        return empregadoController.getAtributoEmpregado(empregadoId, atributo);
    }
    public Integer criarEmpregado(String nome, String endereco, String tipo, String salario, String comissao) throws Exception {
        validarEmpregado(nome, endereco, salario);
        if (comissao.isEmpty()) {
            throw new Exception("Comissao nao pode ser nula.");
        } else if (!isFloat(comissao, "Comissao")) {


        }


        EmpregadoDTO dto = new EmpregadoDTO(nome, endereco,tipo);
        return empregadoController.salvar_empregado(dto);
    }

    public Empregado getEmpregadoPorNome(String nome, Integer indice) throws Exception {
        Empregado empregado_encontrado = empregadoController.busca_id(indice);
        if(!empregado_encontrado.getNome().equals(nome)){
            throw new Exception("Nao ha empregado com esse nome.");
        }
        return empregado_encontrado;
    }

    public void zerarSistema(){

    }

}