package puorg.Spring37301.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class R150 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String electricalCabinet;
    private String idNumber;

    @OneToMany(mappedBy = "r150Fuse",cascade = CascadeType.ALL)
    private Set<Fuse> fuses = new HashSet<>();

    @OneToOne(mappedBy = "r150")
    private HeadWay headWay;

    public R150() {
    }
    public R150(Long id){
        this.id = id;
    }

    public R150(String note){
        this.idNumber = note;
    }

    public R150(Long id, String electricalCabinet,  String idNumber, Set<Fuse> fuses) {
        this.id = id;
        this.electricalCabinet = electricalCabinet;
        this.idNumber = idNumber;
        this.fuses = fuses;
    }
    public void addFuse(Fuse fuse){
        fuses.add(fuse);
        fuse.setR150Fuse(this);
    }
}