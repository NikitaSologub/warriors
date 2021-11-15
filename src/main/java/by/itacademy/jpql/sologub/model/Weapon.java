package by.itacademy.jpql.sologub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "weapon")
public class Weapon extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private WeaponType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer")
    @NotNull
    private WeaponManufacturer manufacturer;
    @Column(name = "serial_number")
    @NotNull
    private String serialNumber;

    @Override
    public AbstractEntity withId(int id) {
        setId(id);
        return this;
    }
}