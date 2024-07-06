package med.voll.api.domain.doctor;

import med.voll.api.domain.direction.DataAddres;

public record DataResponseDoctor(
         Long id,
         String name,
         String email,
         String numberTelf,
         Speciality speciality,
         DataAddres direction
) {

}
