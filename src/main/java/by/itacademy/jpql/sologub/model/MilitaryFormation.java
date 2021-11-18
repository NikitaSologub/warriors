package by.itacademy.jpql.sologub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@NamedQuery(name = "getByTitle", query = "select f from MilitaryFormation f where title=?1")
@Entity
@Table(name = "military_formation")
public class MilitaryFormation extends AbstractEntity {
    @Column(name = "title")
    @NotNull
    private String title;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private Set<Warrior> warriors;

    @Override
    public AbstractEntity withId(int id) {
        setId(id);
        return this;
    }
}