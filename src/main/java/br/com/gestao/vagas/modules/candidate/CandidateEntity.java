package br.com.gestao.vagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] nao deve conter espacos")
    private String username;

    @Email(message = "Por favor, insira um [email] válido")
    private String email;

    @Length(min = 4, max = 8, message = "A senha deve conter entre 4 e 8 caracteres")
    private String password;

    private String description;
    private String curriculum;


}
