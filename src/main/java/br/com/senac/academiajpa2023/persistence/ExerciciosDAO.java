package br.com.senac.academiajpa2023.persistence;

import br.com.senac.academiajpa2023.ucpi2atv5.Exercicios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ExerciciosDAO {

    public void cadastrar(Exercicios e) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception p) {
            em.getTransaction().rollback();
            throw p;
        } finally {
            JPAUtil.closeEtityManager();
        }
    }

    public List<Exercicios> listar(String filtronome_exercicio) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Exercicios> exercicios = null;
        try {
            String textoQuery = "SELECT e FROM Exercicios e "
                    + " WHERE (:nome_exercicio is null OR e.nome_exercicio LIKE :nome_exercicio)";

            Query consulta = em.createQuery(textoQuery);
            consulta.setParameter("nome_exercicio", filtronome_exercicio.isEmpty() ? null : "%" + filtronome_exercicio + "%");
            exercicios = consulta.getResultList();
        } finally {
            JPAUtil.closeEtityManager();
        }
        return exercicios;
    }

    public List<Exercicios> Carregar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM Exercicios e");
            List<Exercicios> exercicios = consulta.getResultList();
            return exercicios;
        } finally {
            JPAUtil.closeEtityManager();
        }
    }
         public void excluir(int id){
      EntityManager em = JPAUtil.getEntityManager();
      try{
          Exercicios exercicios = em.find(Exercicios.class, id);
          if(exercicios != null){
              em.getTransaction().begin();
              em.remove(exercicios);
              em.getTransaction().commit();
          }
      }catch(Exception p){
          em.getTransaction().rollback();
          throw p;
      }
      finally{
         JPAUtil.getEntityManager();
      }
}
}
