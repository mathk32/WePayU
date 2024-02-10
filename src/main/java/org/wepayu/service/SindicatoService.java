package org.wepayu.service;


import jakarta.persistence.EntityManager;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Empregado;
import org.wepayu.domain.entities.Sindicato;

import java.util.List;

public class SindicatoService {
    private DAO<Sindicato> sindicato_dao;
    private EntityManager entity;



    public SindicatoService(DAO<Sindicato> sindicato_dao) {
        this.sindicato_dao = sindicato_dao;
        this.entity = sindicato_dao.getEntityManager();
    }

    public SindicatoService() {

    }

    public Integer save(Sindicato sindicato){
        entity.getTransaction().begin();
        entity.persist(sindicato);
        entity.getTransaction().commit();
        return sindicato.getId();
    }

    public Sindicato find(Integer id) throws Exception {
        Sindicato sindicato = entity.find(Sindicato.class, id);
        if(sindicato != null){
            return sindicato;
        }else{
            throw new Exception("sindicato não encontrado!");
        }
    }

    public void remove(Integer id) throws Exception {
        DAO<Empregado> dao_empregado = new DAO(Empregado.class);
        EntityManager entity_empregado = dao_empregado.getEntityManager();
        entity_empregado.getTransaction().begin();
        Sindicato sindicato_encontrado = entity.find(Sindicato.class, id);
        List<Empregado> empregados = entity_empregado.createNamedQuery("Sindicato.findEmpregadoForId", Empregado.class)
                .setParameter("id", id)
                .getResultList();
        if(empregados.isEmpty()){
            throw new Exception("Empregado nao existe.");
        }
        for (Empregado empregado_encontrado : empregados) {
            empregado_encontrado.setSindicato(null);
            entity_empregado.persist(empregado_encontrado);
        }
        entity_empregado.getTransaction().commit();

        if (sindicato_encontrado != null) {
            entity.getTransaction().begin();
            sindicato_encontrado = entity.merge(sindicato_encontrado);
            entity.remove(sindicato_encontrado);
            entity.getTransaction().commit();
        }
    }

    public EntityManager getEntity() {
        return entity;
    }
}
