package br.com.gestao.vagas.modules.company.repositories;

import br.com.gestao.vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
