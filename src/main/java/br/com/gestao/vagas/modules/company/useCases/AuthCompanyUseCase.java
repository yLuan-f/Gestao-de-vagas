package br.com.gestao.vagas.modules.company.useCases;

import br.com.gestao.vagas.modules.company.dto.AuthCandidateResponseDTO;
import br.com.gestao.vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gestao.vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.gestao.vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {


    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        // verificar se o username existe
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
             () -> new UsernameNotFoundException("Company/password incorrect")
        );

        // verificar se senhas sao iguais
         var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // se nao for igual -> erro
        if (!passwordMatches) {
            throw new AuthenticationException("Company/password incorrect") {};
        }

        // se for igual -> gerar token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("Javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyRespondeDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyRespondeDTO;

    }

}
