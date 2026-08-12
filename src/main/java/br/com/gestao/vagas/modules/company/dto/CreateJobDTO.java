package br.com.gestao.vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Vaga para desenvolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Plano de saude, VR, VA...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Senior, Pleno, Junior...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
