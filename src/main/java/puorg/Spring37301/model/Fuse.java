package puorg.Spring37301.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Date;


@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Fuse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date expire;
    private boolean fuseExpired;
    private String name;
    private Long setting;
    private boolean assigned;
    private String idNumber;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CABINET_ID")
    private Cabinet cabinetFuse;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r150_id")
    private R150 r150Fuse;

    public Fuse() {
    }

    public Fuse(Long id, Date expire, String name, Long setting, String idNumber) {
        this.id = id;
        this.expire = expire;
        this.name = name;
        this.setting = setting;
        this.idNumber = idNumber;
    }
}
