package org.wepayu.service;

import jakarta.persistence.EntityManager;
import org.wepayu.domain.entities.DAO.DAO;
import org.wepayu.domain.entities.Empregado;

public class EmpregadoService {
    private DAO<Empregado> empregado_dao;
    private EntityManager entity;

    public EmpregadoService(DAO<Empregado> empregado_dao){
        this.empregado_dao = empregado_dao;
        this.entity = empregado_dao.getEntityManager();
    }

    public void save(Empregado empregado){
        entity.getTransaction().begin();
        entity.persist(empregado);
        entity.getTransaction().commit();
    }
}
