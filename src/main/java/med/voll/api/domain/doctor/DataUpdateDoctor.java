package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direction.DataAddres;

public record DataUpdateDoctor(
        @NotNull
        Long id,
        String name,
        String document,
        DataAddres direction
) {
}
