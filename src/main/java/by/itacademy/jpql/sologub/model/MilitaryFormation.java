package by.itacademy.jpql.sologub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@Entity
@Table(name ="military_formation" )
public class MilitaryFormation extends AbstractEntity{
    @Column(name = "title")
    @NotNull
    private String title;
//    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @ManyToMany
    private Set<Warrior> warriors;

    @Override
    public AbstractEntity withId(int id) {
        setId(id);
        return this;
    }
}