package by.itacademy.jpql.sologub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "car")
@With
public class Engine extends AbstractEntity implements Serializable {
    private String model;
    private int power;
    private Car car;

    @Override
    public AbstractEntity withId(int id) {
        return null;
    }
}
