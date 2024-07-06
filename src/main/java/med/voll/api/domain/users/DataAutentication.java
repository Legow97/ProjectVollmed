package med.voll.api.domain.users;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DataAutentication(

        @JsonAlias("login")String login,

        @JsonAlias("clave")String password
) {
}
