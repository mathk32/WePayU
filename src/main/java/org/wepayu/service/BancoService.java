package org.wepayu.service;

import jakarta.persistence.EntityManager;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Banco;

public class BancoService {
    private DAO<Banco> banco_dao;
    private EntityManager entity;

    public BancoService(DAO<Banco> banco_dao){
        this.banco_dao = banco_dao;
        this.entity = banco_dao.getEntityManager();
    }

    public BancoService(){

    }

    public Integer save(Banco banco){
        entity.getTransaction().begin();
        entity.persist(banco);
        entity.getTransaction().commit();
        return banco.getId();
    }
}
