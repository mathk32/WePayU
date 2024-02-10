package org.wepayu.controller;

import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Sindicato;
import org.wepayu.service.SindicatoService;

public class SindicatoController {
    private static SindicatoService sindicato_service;
    private static DAO<Sindicato> dao;

    static {

        dao = new DAO<>(Sindicato.class);
        sindicato_service = new SindicatoService(dao);

    }

    public SindicatoController() {

    }

    public Integer salvar(Sindicato sindicato){
        return sindicato_service.save(sindicato);
    }

    public void remover(Integer id) throws Exception {
        sindicato_service.remove(id);
    }

    public Sindicato encontrar_id(Integer id) throws Exception {
        return sindicato_service.find(id);
    }



}
