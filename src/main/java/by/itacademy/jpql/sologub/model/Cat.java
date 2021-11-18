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
@ToString
@With
public class Cat extends AbstractEntity implements Serializable {
    private String name;
    private String color;
    private int age;

    @Override
    public AbstractEntity withId(int id) {
        return null;
    }
}
