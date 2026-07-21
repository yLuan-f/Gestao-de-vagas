package br.com.gestao.vagas.modules.company.useCases;

import br.com.gestao.vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gestao.vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUsecase {


    @Value("${security.token.secret}")
    private String secret;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {

        // verificar se o username existe
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
             () -> {
                 throw new UsernameNotFoundException("Company/password incorrect");
        });

        // verificar se senhas sao iguais
         var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // se nao for igual -> erro
        if (!passwordMatches) {
            throw new AuthenticationException("Company/password incorrect") {};
        }

        // se for igual -> gerar token
        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token = JWT.create().withIssuer("Javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;

    }

}
