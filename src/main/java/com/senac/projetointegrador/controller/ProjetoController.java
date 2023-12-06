package com.senac.projetointegrador.controller;

import com.senac.projetointegrador.model.AlunoEntity;
import com.senac.projetointegrador.model.ExercicioEntity;
import com.senac.projetointegrador.model.Preferencia;
import com.senac.projetointegrador.service.AlunoService;
import com.senac.projetointegrador.service.ExercicioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjetoController {

    @Autowired
    AlunoService alunoservice;

    @Autowired
    ExercicioService exercicioservice;

    @GetMapping("/")
    public String exibirIndex() {
        return "index";
    }

    @GetMapping("/alunos")
    public String exibirAlunos(Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        List<AlunoEntity> alunos = alunoservice.listarTodosAlunos();
        model.addAttribute("alunos", alunos);
        model.addAttribute("css", tema);
        return "lista-alunos"; 
    }

    @GetMapping("/prescricao")
    public String exibirFormulario(Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        model.addAttribute("alunoEntity", new AlunoEntity());
        model.addAttribute("css", tema);
        return "prescricao"; 
    }

    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {
        System.out.println("Estilo escolhido: " + pref.getEstilo());

        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        cookiePrefEstilo.setDomain("localhost");
        cookiePrefEstilo.setHttpOnly(true);
        cookiePrefEstilo.setMaxAge(86400);
        cookiePrefEstilo.setPath("/");
        response.addCookie(cookiePrefEstilo);

        System.out.println("Valor do cookie definido: " + pref.getEstilo());

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/cadastrar")
    public String cadastrarAluno(@Valid @ModelAttribute AlunoEntity alunoEntity, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "prescricao";
        } else {
            alunoservice.criarAluno(alunoEntity);
            model.addAttribute("aluno", alunoEntity);
            return "prescricao";
        }
    }

    @GetMapping("/exibir-aluno/{id}")
    public String exibirAluno(@PathVariable Integer id, Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        AlunoEntity aluno = alunoservice.getAlunoId(id);
        model.addAttribute("aluno", aluno);
                    model.addAttribute("css", tema);

        return "exibir-aluno";
    }

    @GetMapping("/lista-alunos")
    public String listaAlunos(Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        model.addAttribute("alunos", alunoservice.listarTodosAlunos());
                    model.addAttribute("css", tema);

        return "lista-alunos";
    }

    @GetMapping("/cadastro-exercicio")
    public String exibirFormularioExercicio(Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        List<AlunoEntity> alunos = alunoservice.listarTodosAlunos();
        model.addAttribute("alunos", alunos);
        model.addAttribute("exercicio", new ExercicioEntity());
                    model.addAttribute("css", tema);

        return "cadastro-exercicio";
    }

    @PostMapping("/cadastro-exercicio")
    public String cadastrarExercicio(@ModelAttribute ExercicioEntity exercicio, Model model) {
        Integer alunoId = exercicio.getAlunoEntity().getId();
        AlunoEntity aluno = alunoservice.getAlunoId(alunoId);
        exercicio.setAlunoEntity(aluno);
        exercicioservice.criarExercicio(exercicio);
        model.addAttribute("exercicios", exercicioservice.listar(alunoId));
        return "lista-alunos-exercicios";
    }

    @PostMapping("/alunos/deletar/{id}")
    public String deletarAluno(@PathVariable Integer id) {
        alunoservice.deletarAluno(id);
        return "redirect:/lista-alunos";
    }

    @GetMapping("/editar-aluno/{id}")
    public String exibirFormularioEdicao(@PathVariable Integer id, Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        AlunoEntity aluno = alunoservice.getAlunoId(id);
                    model.addAttribute("css", tema);

        model.addAttribute("aluno", aluno);
        return "editar-aluno";
    }

    @PostMapping("/editar-aluno/{id}")
    public String salvarEdicaoAluno(@PathVariable Integer id, @ModelAttribute("aluno") AlunoEntity alunoAtualizado) {
        alunoservice.atualizarAluno(id, alunoAtualizado);
        return "redirect:/lista-alunos";
    }

    @GetMapping("/lista-alunos-exercicios")
    public String exibirListaAlunosExercicios(Model model, @RequestParam(name = "filtro", required = false) String filtro, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
        List<AlunoEntity> alunos;

        if (filtro != null && !filtro.isEmpty()) {
            alunos = alunoservice.listarTodosAlunos().stream()
                    .filter(aluno -> aluno.getNome().toLowerCase().contains(filtro.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
                        model.addAttribute("css", tema);

            alunos = alunoservice.listarTodosAlunos();
        }

        model.addAttribute("alunos", alunos);
        return "lista-alunos-exercicios";
    }


    
    @GetMapping("/editar-exercicio/{alunoId}")
public String exibirFormularioEdicaoExercicio(@PathVariable Integer alunoId, Model model, @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {
    AlunoEntity aluno = alunoservice.getAlunoId(alunoId);

    if (aluno != null) {
        List<ExercicioEntity> exercicios = exercicioservice.listar(alunoId);

        model.addAttribute("aluno", aluno);
        model.addAttribute("exercicios", exercicios);
        model.addAttribute("css", tema);

        model.addAttribute("exercicio", new ExercicioEntity());

        return "editar-exercicio";
    } else {
        return "redirect:/pagina-de-erro";
    }
}

    
@PostMapping("/editar-exercicio/{alunoId}")
public String salvarEdicaoExercicio(
        @PathVariable Integer alunoId,
        @RequestParam Integer exercicioId,
        @ModelAttribute("exercicio") ExercicioEntity exercicioAtualizado,
        Model model) {

    try {
        AlunoEntity aluno = alunoservice.getAlunoId(alunoId);

        if (exercicioservice.existeExercicio(exercicioId)) {
            exercicioAtualizado.setAlunoEntity(aluno);

            exercicioservice.atualizarExercicio(exercicioId, exercicioAtualizado);

            List<ExercicioEntity> exercicios = exercicioservice.listar(alunoId);

            model.addAttribute("aluno", aluno);
            model.addAttribute("exercicios", exercicios);

            System.out.println("Exercício Atualizado: " + exercicioAtualizado);

            return "editar-exercicio";
        } else {
            model.addAttribute("mensagemErro", "Exercício com ID " + exercicioId + " não encontrado.");

            return "editar-exercicio";
        }
    } catch (Exception e) {
        model.addAttribute("mensagemErro", "Erro ao editar o exercício");

        return "editar-exercicio";
    }
}


    @PostMapping("/deletar-exercicio/{exercicioId}")
    public String deletarExercicio(@PathVariable Integer exercicioId, Model model) {
        ExercicioEntity exercicio = exercicioservice.getExercicioId(exercicioId);

        Integer alunoId = exercicio.getAlunoEntity().getId();

        exercicioservice.deletarExercicio(exercicioId);

        List<ExercicioEntity> exercicios = exercicioservice.listar(alunoId);

        model.addAttribute("aluno", exercicio.getAlunoEntity());
        model.addAttribute("exercicios", exercicios);

        return "lista-alunos";
    }

}
