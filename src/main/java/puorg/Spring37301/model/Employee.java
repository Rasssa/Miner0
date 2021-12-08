package puorg.Spring37301.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String squad;
    private boolean present;
    private Long shift;
    private boolean filter;

    @ManyToMany
    private Set<HeadWay> headWays = new HashSet<>();

    public Employee(String firstName, String lastName, String squad, Long shift) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.squad = squad;
        this.shift = shift;
    }
    public void addHeadway(HeadWay headWay){
        headWays.add(headWay);
    }
}