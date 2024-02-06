package org.wepayu.controller;

import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.dto.EmpregadoDTO;
import org.wepayu.domain.dto.EmpregadoSPDTO;
import org.wepayu.domain.entities.Empregado;
import org.wepayu.service.EmpregadoService;

import java.util.List;
import java.util.stream.Collectors;

public class EmpregadoController {
    private static EmpregadoService empregado_service;
    private static DAO<Empregado> dao;

    static{

         dao = new DAO<>(Empregado.class);
         empregado_service = new EmpregadoService(dao);

    }


    public EmpregadoController(){

    }


    public Integer salvar_empregado(EmpregadoDTO empregado_dto){
        return empregado_service.save(empregado_dto);
    }

    public Empregado busca_id(Integer id ) throws Exception {
        return empregado_service.find_by_id(id);
    }

    public void remover_id(Integer id) throws Exception {
        empregado_service.delete(id);
    }
    public List<String> encontrar_empregados(){
       return empregado_service.findAll().stream().map(empregado -> {
            return empregado.getNome();
        }).collect(Collectors.toList());
    }
    public void atualizar_dados_pessoais(Integer id, EmpregadoDTO empregado_dto) throws Exception {
        empregado_service.update_dados_pessoais(id, empregado_dto);
    }

    public void atualizar_sinicato_pagamento(EmpregadoSPDTO empregadosp_dto, Integer id) throws Exception {
        empregado_service.update_sindicato_agenda(empregadosp_dto,id);

    }
}

