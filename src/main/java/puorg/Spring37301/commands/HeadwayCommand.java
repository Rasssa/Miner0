package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.Employee;
import puorg.Spring37301.model.R150;
import puorg.Spring37301.model.Report;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class HeadwayCommand {
    private Long id;
    private String deck;
    private Long progress;
    private boolean working;
    private R150 r150;
    private Cabinet cabinet;
    private Set<Employee> employees;
    private Report reports;
    private Long cabinetNew;
    private Long r150New;
    private boolean change;
    private boolean change2;
    private String employe;
    private boolean divided;
}
