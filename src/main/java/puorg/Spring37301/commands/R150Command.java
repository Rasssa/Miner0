package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puorg.Spring37301.model.Fuse;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class R150Command {
    private Long id;
    private String electricalCabinet;
    private String idNumber;
    private Set<Fuse> fuses;

}
