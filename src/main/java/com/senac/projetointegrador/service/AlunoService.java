package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.AlunoRepository;
import com.senac.projetointegrador.exception.ResourceNotFoundException;
import com.senac.projetointegrador.model.AlunoEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AlunoService {

    @Autowired

    AlunoRepository alunorepository;

    public AlunoEntity criarAluno(AlunoEntity aluno) {

        aluno.setId(null);

        alunorepository.save(aluno);

        return aluno;

    }

    public AlunoEntity atualizarAluno(Integer alunoId, AlunoEntity alunoRequest) {
        AlunoEntity alunoExistente = getAlunoId(alunoId);

        alunoExistente.setNome(alunoRequest.getNome());
        alunoExistente.setCpf(alunoRequest.getCpf());
        alunoExistente.setEmail(alunoRequest.getEmail());
        alunoExistente.setEndereco(alunoRequest.getEndereco());
        alunoExistente.setTelefone(alunoRequest.getTelefone());

        alunorepository.save(alunoExistente);

        return alunoExistente;
    }

    public AlunoEntity getAlunoId(Integer alunoId) {

        return alunorepository.findById(alunoId).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado " + alunoId));                

    }

    public List<AlunoEntity> listarTodosAlunos() {

        return alunorepository.findAll();

    }

    public void deletarAluno(Integer alunoId) {

        AlunoEntity aluno = getAlunoId(alunoId);

        alunorepository.deleteById(aluno.getId());

    }
    
     public List<AlunoEntity> listarAlunosPorSeries(int series) {
        return alunorepository.findAll().stream()
                .filter(aluno -> aluno.getExercicio().stream()
                        .anyMatch(exercicio -> exercicio.getSeries() >= series))
                .collect(Collectors.toList());
    }
}
