package br.com.gestao.vagas.modules.company.useCases;

import br.com.gestao.vagas.exceptions.CompanyNotFoundException;
import br.com.gestao.vagas.modules.company.entities.JobEntity;
import br.com.gestao.vagas.modules.company.repositories.CompanyRepository;
import br.com.gestao.vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository JobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute (JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompanyID()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.JobRepository.save(jobEntity);
    }
}
