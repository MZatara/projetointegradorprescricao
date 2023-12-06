package br.com.senac.academiajpa2023.ucpi2atv5;


import br.com.senac.academiajpa2023.persistence.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

     public Usuario validaLogin(String login, String password){
        
        Usuario user = null;
        
        EntityManager em = JPAUtil.getEntityManager();
        try{

            TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nomePar AND u.senha = :senhaPar",Usuario.class);
            consulta.setParameter("nomePar", login);
            consulta.setParameter("senhaPar", password);
            
            if (login.equals(consulta.getSingleResult().getNome()) && password.equals(consulta.getSingleResult().getSenha())){
                user = consulta.getSingleResult();
            }
            
        }catch (Exception e) {
           System.out.println("Erro na validação " + e.getMessage());
        }
        return user;
    }
}
