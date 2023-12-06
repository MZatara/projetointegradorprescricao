
package com.senac.projetointegrador.data;

import com.senac.projetointegrador.model.ExercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<ExercicioEntity, Integer> {
List<ExercicioEntity> findByAlunoEntityId(Integer id);
}

