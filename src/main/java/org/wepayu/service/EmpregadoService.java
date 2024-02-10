package org.wepayu.service;

import jakarta.persistence.EntityManager;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.dto.EmpregadoDTO;
import org.wepayu.domain.dto.EmpregadoSPDTO;
import org.wepayu.domain.entities.Agenda;
import org.wepayu.domain.entities.Empregado;
import org.wepayu.domain.entities.Sindicato;
import org.wepayu.domain.entities.enums.Contrato;

import java.util.List;
import java.util.Optional;


public class EmpregadoService {
    private DAO<Empregado> empregado_dao;
    private EntityManager entity;

    public EmpregadoService(DAO<Empregado> empregado_dao){
        this.empregado_dao = empregado_dao;
        this.entity = empregado_dao.getEntityManager();
    }

    public EmpregadoService(){

    }


    public Integer save(EmpregadoDTO empregado_dto){
        Empregado empregado = converter_dto(empregado_dto);
        entity.getTransaction().begin();
        entity.persist(empregado);
        entity.getTransaction().commit();
        return empregado.getId();
    }

    public Empregado find_by_id(Integer id) throws Exception {

        Empregado empregado = entity.find(Empregado.class, id);
        if(empregado != null){
            return empregado;
        }else{
             throw new Exception("empregado não encontrado!");
        }
    }

    public void delete(Integer id) throws Exception {
        entity.getTransaction().begin();
        Empregado empregado_encontrado = entity.find(Empregado.class, id);
        if (empregado_encontrado != null) {
            if(empregado_encontrado.getSindicato() != null){
                DAO<Sindicato> sindicato_dao = new DAO<>(Sindicato.class);
                SindicatoService sindicato_service = new SindicatoService(sindicato_dao);
                sindicato_service.remove(empregado_encontrado.getSindicato().getId());
            }
            entity.remove(empregado_encontrado);
        }
        entity.getTransaction().commit();
    }

    public List<Empregado> findAll(){
        List<Empregado> empregados = entity.createNamedQuery("Empregado.findAll", Empregado.class)
                .getResultList();
        return empregados;
    }

    public void update_dados_pessoais(Integer id, EmpregadoDTO empregado_dto) throws Exception {
        entity.getTransaction().begin();
        Empregado empregado_antigo = find_by_id(id);
        empregado_antigo.setNome(empregado_dto.nome());
        empregado_antigo.setEndereco(empregado_dto.endereco());
        empregado_antigo.setTipo(Contrato.valueOf(empregado_dto.contrato()));
        if(empregado_dto.sindicato_id() != null){
            Integer id_sindicato = empregado_dto.sindicato_id();
            DAO<Sindicato> sindicato_dao = new DAO<>(Sindicato.class);
            SindicatoService sindicato_service = new SindicatoService(sindicato_dao);
            Sindicato sindicato_encontrado = sindicato_service.find(id_sindicato);
            empregado_antigo.setSindicato(sindicato_encontrado);
        } else {
            empregado_antigo.setSindicato(null);
        }
        if(empregado_dto.pagamento_id() != null){
            Integer id_pagamento = empregado_dto.pagamento_id();
            DAO<Agenda> agenda_dao = new DAO<>(Agenda.class);
            AgendaService agenda_service = new AgendaService(agenda_dao);
            Agenda agenda_encontrado = agenda_service.find(id_pagamento);
            empregado_antigo.setPagamento(agenda_encontrado);

        } else {
        empregado_antigo.setPagamento(null);
    }
        entity.persist(empregado_antigo);
        entity.getTransaction().commit();
    }

    public void update_sindicato_agenda(EmpregadoSPDTO empregadosp_dto, Integer id) throws Exception {
        entity.getTransaction().begin();
        Empregado empregado_encontrado = entity.find(Empregado.class, id);
        Integer id_sindicato = empregadosp_dto.sindicato_id();
        DAO<Sindicato> sindicato_dao = new DAO<>(Sindicato.class);
        SindicatoService sindicato_service = new SindicatoService(sindicato_dao);
        DAO<Agenda> agenda_dao = new DAO<>(Agenda.class);
        AgendaService agenda_service = new AgendaService(agenda_dao);
        Sindicato sindicato_encontrado = sindicato_service.find(id_sindicato);
        empregado_encontrado.setSindicato(sindicato_encontrado);
        Integer id_pagamento = empregadosp_dto.pagamento_id();
        Agenda agenda_encontrado = agenda_service.find(id_pagamento);
        empregado_encontrado.setPagamento(agenda_encontrado);
        entity.getTransaction().commit();
    }


    private Empregado converter_dto(EmpregadoDTO empregado_dto) {

        DAO<Sindicato> sindicato_dao = new DAO<>(Sindicato.class);
        SindicatoService sindicato_service = new SindicatoService(sindicato_dao);
        EntityManager entity_sindicato = sindicato_service.getEntity();
        Optional<Sindicato> id_sindicato = null;
        if (empregado_dto.sindicato_id() != null) {
            id_sindicato = Optional.ofNullable(entity_sindicato.find(Sindicato.class, empregado_dto.sindicato_id()));
        }
        Sindicato sindicato = null;
        if (id_sindicato != null && id_sindicato.isPresent()) {
            sindicato = id_sindicato.get();
        }


        Empregado empregado = Empregado.builder()
                .nome(empregado_dto.nome())
                .endereco(empregado_dto.endereco())
                .tipo(empregado_dto.contrato())
                .sindicato(sindicato)
                .pagamento(null)
                .build();
        return empregado;
    }


}
