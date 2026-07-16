package br.com.gestao.vagas.modules.company.useCases;

import br.com.gestao.vagas.modules.company.entities.JobEntity;
import br.com.gestao.vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository JobRepository;

    public JobEntity execute (JobEntity jobEntity) {
        return this.JobRepository.save(jobEntity);
    }
}
