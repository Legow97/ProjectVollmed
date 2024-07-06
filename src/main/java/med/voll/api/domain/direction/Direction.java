package med.voll.api.domain.direction;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {
    private String street;
    private String distric;
    private String city;
    private int number;
    private String complement;

    public Direction(DataAddres dataAddres) {
        this.street = dataAddres.street();
        this.distric= dataAddres.distric();
        this.city = dataAddres.city();
        this.number = dataAddres.number();
        this.complement= dataAddres.complement();
    }

    public Direction dataUpdate(DataAddres direction) {
        this.street = direction.street();
        this.distric= direction.distric();
        this.city = direction.city();
        this.number = direction.number();
        this.complement= direction.complement();
        return this;
    }
}
