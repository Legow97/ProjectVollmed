package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direction.Direction;

@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String numberTelf;
    private String document;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Direction direction;

    public Doctor(DataRegisterDoctor dataRegisterDoctor) {
        this.name= dataRegisterDoctor.name();
        this.email= dataRegisterDoctor.email();
        this.numberTelf= dataRegisterDoctor.numberTelf();
        this.document= dataRegisterDoctor.document();
        this.active= true;
        this.speciality= dataRegisterDoctor.speciality();
        this.direction= new Direction(dataRegisterDoctor.dataAddres());
    }

    public void dataUpdate(DataUpdateDoctor dataUpdateDoctor) {
        if (dataUpdateDoctor.name() != null) {this.name = dataUpdateDoctor.name();}
        if (dataUpdateDoctor.document() != null) {this.document = dataUpdateDoctor.document();}
        if (dataUpdateDoctor.direction() != null){this.direction = direction.dataUpdate(dataUpdateDoctor.direction());}

    }

    public void desactiveDoctor() {
        this.active = false;
    }
}
