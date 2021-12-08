package puorg.Spring37301.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long traffo1;
    private Long traffo2;
    private String type;
    private String idNumber;

    @OneToMany(mappedBy = "cabinetFuse",cascade = CascadeType.ALL)
    private Set<Fuse> fuses = new HashSet<>();

    @OneToOne(mappedBy = "cabinet")
    private HeadWay headWay;

    public Cabinet() {
    }

    public Cabinet(Long id) {
        this.id = id;
    }

    public Cabinet(Long id, Long traffo1, Long traffo2, String type, String idNumber, Set<Fuse> fuses) {
        this.id = id;
        this.traffo1 = traffo1;
        this.traffo2 = traffo2;
        this.type = type;
        this.idNumber = idNumber;
        this.fuses = fuses;
    }


    public void addFuse(Fuse fuse){
        fuses.add(fuse);
        fuse.setCabinetFuse(this);
    }
}

