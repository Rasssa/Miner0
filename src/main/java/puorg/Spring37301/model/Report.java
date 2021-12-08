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
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private int shift;
    private String description;
    private boolean repaired;
    private boolean working;

    @ManyToMany(mappedBy = "report")
    private Set<Maintenance> maintenances = new HashSet<>();

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "headWay_id")
    private HeadWay headWay;

    public Report (){
    }

    public Report(Long id, int shift, String description, boolean repaired) {
        this.id = id;
        this.shift = shift;
        this.description = description;
        this.repaired = repaired;
    }

    public void addMaintenance(Maintenance maintenance){
        maintenances.add(maintenance);
    }

}
