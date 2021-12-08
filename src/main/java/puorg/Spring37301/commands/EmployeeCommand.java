package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puorg.Spring37301.model.HeadWay;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String squad;
    private Long headWayId;
    private Set<HeadWay> headWays;
    private Long shift;
}
