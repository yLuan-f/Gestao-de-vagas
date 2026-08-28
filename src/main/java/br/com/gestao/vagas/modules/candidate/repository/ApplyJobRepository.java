package br.com.gestao.vagas.modules.candidate.repository;

import br.com.gestao.vagas.modules.candidate.entities.ApplyJobEntity;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
