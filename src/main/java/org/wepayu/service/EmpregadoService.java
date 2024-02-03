package org.wepayu.service;

import jakarta.persistence.EntityManager;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.dto.EmpregadoDTO;
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


    public void save(EmpregadoDTO empregado_dto){
        Empregado empregado = converter_dto(empregado_dto);
        entity.getTransaction().begin();
        entity.persist(empregado);
        entity.getTransaction().commit();
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
        entity.persist(empregado_antigo);
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
