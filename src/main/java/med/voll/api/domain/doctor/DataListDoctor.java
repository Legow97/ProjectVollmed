package med.voll.api.domain.doctor;

public record DataListDoctor(
        Long id,
        String name,
        String speciality,
        String document,
        String email
) {

    public DataListDoctor(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getSpeciality().toString(), doctor.getDocument(), doctor.getEmail());
    }
}
