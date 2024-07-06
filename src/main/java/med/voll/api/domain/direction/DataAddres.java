package med.voll.api.domain.direction;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataAddres(
        @NotBlank
        @JsonAlias("calle")String street,
        @NotBlank
        @JsonAlias("distrito")String distric,
        @NotBlank
        @JsonAlias("ciudad")String city,
        @NotNull
        @JsonAlias("numero")Integer number,
        @NotBlank
        @JsonAlias("complemento")String complement
) {
}
