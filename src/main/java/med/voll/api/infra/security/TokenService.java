package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.users.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //@Value("${api.security.secret}")
    private String apiSecret = "123456";

    public String tokenGenerator(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("vollmed")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dateGenerateExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    private Instant dateGenerateExpiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
    public String getSubject(String token) {
        if(token == null){
            throw new RuntimeException();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("vollmed")
                    .build()
                    .verify(token);

            String subject = decodedJWT.getSubject();
            if (subject == null) {
                throw new RuntimeException("Subject is null");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println(exception.toString());
            throw new RuntimeException("Invalid JWT token", exception);
        }
    }
//        DecodedJWT verifier = null;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(apiScret);
//            verifier = JWT.require(algorithm)
//                    // specify any specific claim validations
//                    .withIssuer("vollmed")
//                    // reusable verifier instance
//                    .build()
//                    .verify(token);
//
//            verifier.getSubject();
//            //decodedJWT = verifier.verify(token);
//        } catch (JWTVerificationException exception){
//            // Invalid signature/claims
//            System.out.println(exception.toString());
//        }
//        if(verifier.getSubject() == null){
//            throw new RuntimeException("Verifier Inválido");
//
//        }
//        return verifier.getSubject();
//    }

}
