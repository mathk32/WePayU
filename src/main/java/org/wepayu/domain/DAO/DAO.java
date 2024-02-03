package org.wepayu.domain.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class DAO <E>{
    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> classe;

    static {
        try{
            emf = Persistence.createEntityManagerFactory("wepayu");
        }catch(Exception e){

        }

    }

    public DAO(){

    }
    public DAO(Class<E> classe){
        this.classe = classe;
        em = emf.createEntityManager();
    }
    public EntityManager getEntityManager(){
        return em;
    }
}
