package org.wepayu.controller;

import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Banco;
import org.wepayu.service.BancoService;


public class BancoController {
    private static BancoService banco_service;
    private static DAO<Banco> dao;

    static{

        dao = new DAO<>(Banco.class);
        banco_service = new BancoService(dao);

    }

    public Integer salvar(Banco banco){
        return banco_service.save(banco);
    }

}
