package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.Maintenance;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ReportCommand {
    private Long id;
    private int shift;
    private String description;
    private Set<Maintenance> maintenances;
    private HeadWay headWay;
    private boolean working;
}
