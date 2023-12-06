
package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.ExercicioRepository;
import com.senac.projetointegrador.model.ExercicioEntity;
import java.util.List;
import static org.hibernate.loader.internal.AliasConstantsHelper.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExercicioService {

    @Autowired

    ExercicioRepository exercicioRepository;

    public ExercicioEntity criarExercicio(ExercicioEntity exercicios) {

        exercicios.setId(null);

        exercicioRepository.save(exercicios);

        return exercicios;

    }
    
    public List<ExercicioEntity> listar (Integer id){
        
        return exercicioRepository.findByAlunoEntityId(id);
        
    }
    
public ExercicioEntity atualizarExercicio(Integer exercicioId, ExercicioEntity exercicioRequest) {
    if (exercicioId != null) {
        ExercicioEntity exercicioExistente = getExercicioId(exercicioId);
        exercicioExistente.setNome_exercicio(exercicioRequest.getNome_exercicio());
        exercicioExistente.setRepeticoes(exercicioRequest.getRepeticoes());
        exercicioExistente.setSeries(exercicioRequest.getSeries());

        exercicioRepository.save(exercicioExistente);

        return exercicioExistente;
    } else {
    
        throw new IllegalArgumentException("O ID do exercício não pode ser nulo");
    }
}



 
   public ExercicioEntity getExercicioId(Integer exercicioId) {

        return exercicioRepository.findById(exercicioId).orElse(null);

    }
   public void deletarExercicio(Integer exercicioId) {

        ExercicioEntity exercicio = getExercicioId(exercicioId);

        exercicioRepository.deleteById(exercicio.getId());

    }
   
   public boolean existeExercicio(Integer exercicioId) {
    return exercicioRepository.existsById(exercicioId);
}

}
