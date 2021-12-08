package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puorg.Spring37301.model.Report;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MaintenanceCommand {
    private Long id;
    private String name;
    private String description;
    private Long timeForMaintenance;
    private Long countOfIncidents;
    private Long employesOrder;
}
