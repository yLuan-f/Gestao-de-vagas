package br.com.gestao.vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate") // da o nome a tabela no banco de dados
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Rodrigo de Souza", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] nao deve conter espacos")
    @Schema(example = "rodri", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @Email(message = "Por favor, insira um [email] válido")
    @Schema(example = "rodri@example.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Email do candidato")
    private String email;

    @Length (min = 10, max = 100, message = "O campo [password] deve ter entre 10 e 100 caracteres")
    @Schema(example = "admin12345", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;

    @Schema(example = "Desenvolvedor Python", requiredMode = Schema.RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;

    private String curriculum;

    // adiciona a data e hora da criacao de um novo candidato automaticamente
    @CreationTimestamp
    private LocalDateTime createdAt;

}
