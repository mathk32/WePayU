package org.wepayu.service;

import jakarta.persistence.EntityManager;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.dto.AgendaBDTO;
import org.wepayu.domain.entities.Agenda;
import org.wepayu.domain.entities.Banco;
import org.wepayu.domain.entities.Empregado;

import java.time.LocalDate;
import java.util.List;


public class AgendaService {
    private DAO<Agenda> agenda_dao;
    private EntityManager entity;



    public AgendaService(DAO<Agenda> agenda_dao) {
        this.agenda_dao = agenda_dao;
        this.entity = agenda_dao.getEntityManager();
    }

    public AgendaService() {

    }
    public Integer save(Agenda agenda){
        entity.getTransaction().begin();
        entity.persist(agenda);
        entity.getTransaction().commit();
        return agenda.getId();
    }

    public void update_banco(Integer id, AgendaBDTO agenda_banco_dto){
        if (agenda_banco_dto != null){
            entity.getTransaction().begin();
            DAO<Banco> dao_banco = new DAO<>(Banco.class);
            EntityManager entity_banco = dao_banco.getEntityManager();
            Agenda agenda_encontrada = entity.find(Agenda.class, id);
            Banco banco_encontrado = entity_banco.find(Banco.class, agenda_banco_dto.id());
            agenda_encontrada.setBanco(banco_encontrado);
            entity.getTransaction().commit();
        }
    }
    public Agenda find(Integer id) throws Exception {
        Agenda agenda = entity.find(Agenda.class, id);
        if(agenda != null){
            return agenda;
        }else{
            throw new Exception("agenda não encontrado!");
        }
    }

    public void update_day(Integer id, LocalDate dia_novo){
        Agenda agenda_encontrada = entity.find(Agenda.class,id);
        agenda_encontrada.setDia(dia_novo);
        entity.persist(agenda_encontrada);
    }
    public void remove(Integer id){
        DAO<Empregado> dao_empregado = new DAO(Empregado.class);
        EntityManager entity_empregado = dao_empregado.getEntityManager();
        entity_empregado.getTransaction().begin();
        Agenda agenda_encontrado = entity.find(Agenda.class, id);
        List<Empregado> empregados = entity_empregado.createNamedQuery("Sindicato.findEmpregadoForId", Empregado.class)
                .setParameter("id", id)
                .getResultList();
        for (Empregado empregado_encontrado : empregados) {
            empregado_encontrado.setPagamento(null);
            entity_empregado.persist(empregado_encontrado);
        }
        entity_empregado.getTransaction().commit();

        if (agenda_encontrado != null) {
            entity.getTransaction().begin();
            agenda_encontrado = entity.merge(agenda_encontrado);
            entity.remove(agenda_encontrado);
            entity.getTransaction().commit();
        }
    }

}
