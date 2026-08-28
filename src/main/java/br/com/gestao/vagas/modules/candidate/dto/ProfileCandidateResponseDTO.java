package br.com.gestao.vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "maria")
    private String name;

    @Schema(example = "maris")
    private String username;

    @Schema(example = "maria@example.com")
    private String email;
    private UUID id;
    
    @Schema(example = "Candidata com 5 anos de experiencia em desenvolvimento de software.")
    private String description;
}
