package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.DataAutentication;
import med.voll.api.domain.users.User;
import med.voll.api.infra.security.DataJWTtoken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticationUser(@RequestBody @Valid DataAutentication dataAutentication){
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataAutentication.login(),dataAutentication.password());
        authenticationManager.authenticate(authToken);
        var userAutenticated = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.tokenGenerator((User) userAutenticated.getPrincipal());
        return ResponseEntity.ok(new DataJWTtoken(JWTtoken));
    }
}
