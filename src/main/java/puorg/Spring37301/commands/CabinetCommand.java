package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puorg.Spring37301.model.Fuse;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CabinetCommand {
    private Long id;
    private Long traffo1;
    private Long traffo2;
    private String type;
    private String idNumber;
    private Set<Fuse> fuses;
    private Set<Fuse> fusesAdd;

}
