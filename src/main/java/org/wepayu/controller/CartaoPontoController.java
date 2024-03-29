package org.wepayu.controller;

import org.wepayu.domain.DAO.DAO;

import org.wepayu.domain.entities.Cartao_ponto;
import org.wepayu.service.CartaoPontoService;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CartaoPontoController {
    private static CartaoPontoService cartaoponto_service;
    private static DAO<Cartao_ponto> dao;

    static{

        dao = new DAO<>(Cartao_ponto.class);
        cartaoponto_service = new CartaoPontoService(dao);

    }

    public Integer Entry(LocalTime entrada, Integer id, LocalDate dia,Float vendas){
        return cartaoponto_service.entrada(entrada, id, dia,vendas);
    }

    public  void get_out(LocalTime saida, LocalDate dia){
         cartaoponto_service.saida(saida,dia);
    }


}
