package org.wepayu.controller;

import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Agenda;
import org.wepayu.service.AgendaService;

import java.time.LocalDate;

public class AgendaController {
    private static AgendaService agenda_service;
    private static DAO<Agenda> dao;

    static{

        dao = new DAO<>(Agenda.class);
        agenda_service = new AgendaService(dao);

    }
    public AgendaController(){

    }

    public Integer salvar(Agenda  agenda){
        return agenda_service.save(agenda);
    }

    public Agenda buscar_por_id(Integer id) throws Exception {
       return  agenda_service.find(id);
    }

    public void remover(Integer id){
        agenda_service.remove(id);
    }

    public void atualizar_dia(Integer id, LocalDate dia){
        agenda_service.update_day(id, dia);
    }

}
