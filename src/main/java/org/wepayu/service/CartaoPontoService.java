package org.wepayu.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.wepayu.domain.DAO.DAO;
import org.wepayu.domain.entities.Cartao_ponto;
import org.wepayu.domain.entities.Empregado;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CartaoPontoService {
    private DAO<Cartao_ponto> cartao_dao;
    private EntityManager entity;
    private static DAO<Empregado> empregado_dao;
    private static EntityManager entity_empregado;

    static {
        empregado_dao = new DAO<>(Empregado.class);
        entity_empregado = empregado_dao.getEntityManager();
    }

    public CartaoPontoService(DAO<Cartao_ponto> cartao_dao) {
        this.cartao_dao = cartao_dao;
        this.entity = cartao_dao.getEntityManager();
    }

    public CartaoPontoService() {

    }

    public Integer entrada(LocalTime horario_entrada, Integer id, LocalDate dia, Float vendas) {
        entity.getTransaction().begin();
        Empregado empregado_encontrado = entity_empregado.find(Empregado.class, id);
        Cartao_ponto cartao = new Cartao_ponto(horario_entrada, null, empregado_encontrado, dia, vendas);
        entity.persist(cartao);
        entity.getTransaction().commit();
        return cartao.getId();
    }

    public void saida(LocalTime horario_saida, LocalDate dia) {
        entity.getTransaction().begin();
        Query query = entity.createNamedQuery("Cartao_ponto.updateSaida");
        query.setParameter("saida", horario_saida);
        query.setParameter("dia", dia);
        query.executeUpdate();
        entity.getTransaction().commit();
    }

}