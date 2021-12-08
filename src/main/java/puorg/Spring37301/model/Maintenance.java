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
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private Long timeForMaintenance;
    private Long countOfIncidents;
    private Long employesOrder;

    @ToString.Exclude
    @ManyToMany
    private Set<Report> report = new HashSet<>();

    public Maintenance() {
    }

    public Maintenance(Long id, String name,String description, Long timeForMaintenance, Long countOfIncidents, Long employesOrder) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.timeForMaintenance = timeForMaintenance;
        this.countOfIncidents = countOfIncidents;
        this.employesOrder = employesOrder;
    }
    public void addReport(Report reporte){
        report.add(reporte);
    }
}
