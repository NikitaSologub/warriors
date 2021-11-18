package by.itacademy.jpql.sologub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@Entity
@Table(name = "warrior_info")
public class WarriorInfo extends AbstractEntity {
    @Column(name = "firstname")
    @NotNull
    private String firstname;
    @Column(name = "lastname")
    @NotNull
    private String lastname;
    @Column(name = "age")
    @NotNull
    private Integer age;

    @Override
    public AbstractEntity withId(int id) {
        setId(id);
        return this;
    }
}