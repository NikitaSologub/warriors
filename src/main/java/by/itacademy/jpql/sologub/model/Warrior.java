package by.itacademy.jpql.sologub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@With
@Entity
@Table(name = "warrior")
public class Warrior extends AbstractEntity {
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "info_id")
    @NotNull
    private WarriorInfo info;
    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    @NotNull
    private MilitaryRank rank;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @NotNull
    private Weapon weapon;

    @Override
    public AbstractEntity withId(int id) {
        return null;
    }
}