package org.wepayu.service;

import jakarta.persistence.EntityManager;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Sindicato;

public class SindicatoService {
    private DAO<Sindicato> sindicato_dao;
    private EntityManager entity;

    public SindicatoService(DAO<Sindicato> sindicato_dao) {
        this.sindicato_dao = sindicato_dao;
        this.entity = sindicato_dao.getEntityManager();
    }

    public SindicatoService() {

    }

    public EntityManager getEntity() {
        return entity;
    }
}
