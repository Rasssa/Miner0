package puorg.Spring37301.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class HeadWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String deck;
    private Long progress;
    private boolean working;
    private Long r150New;
    private Long cabinetNew;
    private boolean change;
    private boolean change2;
    private boolean divided;
    private String employee;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private Report reports;

    @ToString.Exclude
    @ManyToMany(mappedBy = "headWays")
    private Set<Employee> employees = new HashSet<>();

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "r150_Id")
    private R150 r150;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "cabinet_Id")
    private Cabinet cabinet;

    public HeadWay() {
    }

    public HeadWay(Long id, String deck, Long progress, boolean working, R150 r150, Cabinet cabinet) {
        this.id = id;
        this.deck = deck;
        this.progress = progress;
        this.working = working;
        this.r150 = r150;
        this.cabinet = cabinet;
    }

    public R150 getR150() {
        return r150;
    }

    public void setR150(R150 r150) {
        this.r150 = r150;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    /*public void addReport(Report report){
        reports.add(report);
    }*/
    public void addEmployee(Employee employee){
        employees.add(employee);
    }

}
