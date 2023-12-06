
package com.senac.projetointegrador.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table; 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data; 
import org.hibernate.validator.constraints.br.CPF;
@Data 
@Entity 
@Table(name="Aluno") 
public class AlunoEntity { 
    
@Id 
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
@NotBlank(message="CPF é obrigatório")
@CPF(message="CPF Inválido")
private String cpf;
@NotBlank(message="Nome é obrigatório")
@Size(min=2, message="Informe ao menos 2 caracteres para o campo nome")
private String nome; 
private String telefone; 
@NotBlank(message="Email é obrigatório")
@Email(message="E-mail inválido")
private String email; 
private String endereco;

    @OneToMany(mappedBy = "alunoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExercicioEntity> exercicio;
} 