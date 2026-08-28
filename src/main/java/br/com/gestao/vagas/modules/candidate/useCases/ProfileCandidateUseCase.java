package br.com.gestao.vagas.modules.candidate.useCases;

import br.com.gestao.vagas.exceptions.UserNotFoundException;
import br.com.gestao.vagas.modules.candidate.repository.CandidateRepository;
import br.com.gestao.vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute (UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .name(candidate.getName())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .id(candidate.getId())
                .build();

        return candidateDTO;
    }
}
