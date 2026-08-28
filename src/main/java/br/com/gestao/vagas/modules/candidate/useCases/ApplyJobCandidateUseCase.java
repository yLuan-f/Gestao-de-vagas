package br.com.gestao.vagas.modules.candidate.useCases;

import br.com.gestao.vagas.exceptions.JobNotFoundException;
import br.com.gestao.vagas.exceptions.UserNotFoundException;
import br.com.gestao.vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.gestao.vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gestao.vagas.modules.candidate.repository.CandidateRepository;
import br.com.gestao.vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        // validar se candidato existe
        this.candidateRepository.findById(idCandidate).orElseThrow(() -> new UserNotFoundException());

        // validar se vaga existe
        this.jobRepository.findById(idJob).orElseThrow(() -> new JobNotFoundException());

        // candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
                .candidateID(idCandidate)
                .jobId(idJob)
                .build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
