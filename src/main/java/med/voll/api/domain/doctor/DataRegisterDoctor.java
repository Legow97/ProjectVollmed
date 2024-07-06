package med.voll.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direction.DataAddres;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataRegisterDoctor(

        @NotBlank
        @JsonAlias("nombre") String name,
        @NotBlank
        @Email
        @JsonAlias("email") String email,
        @NotBlank
        @JsonAlias("telefono") String numberTelf,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        @JsonAlias("documento") String document,
        @NotNull
        @JsonAlias("especializacion") Speciality speciality,
        @NotNull
        @Valid
        @JsonAlias("direccion") DataAddres dataAddres
) {
}
