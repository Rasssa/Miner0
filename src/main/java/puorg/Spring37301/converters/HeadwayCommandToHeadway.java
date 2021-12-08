package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.EmployeeCommand;
import puorg.Spring37301.commands.HeadwayCommand;
import puorg.Spring37301.model.*;
import puorg.Spring37301.repositories.CabinetRepository;
import puorg.Spring37301.repositories.EmployeeRepository;
import puorg.Spring37301.repositories.HeadWayRepository;
import puorg.Spring37301.repositories.R150Repository;

import java.util.Optional;
import java.util.Set;

@Component
public class HeadwayCommandToHeadway implements Converter<HeadwayCommand, HeadWay> {

    private R150Repository r150Repository;
    private CabinetRepository cabinetRepository;
    private EmployeeRepository employeeRepository;
    private HeadWayRepository headWayRepository;

    public HeadwayCommandToHeadway(R150Repository r150Repository, CabinetRepository cabinetRepository,
                                   EmployeeRepository employeeRepository, HeadWayRepository headWayRepository){
        this.r150Repository = r150Repository;
        this.cabinetRepository = cabinetRepository;
        this.employeeRepository = employeeRepository;
        this.headWayRepository = headWayRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public HeadWay convert(HeadwayCommand source){

        if (source ==null){
            return null;
        }

        final HeadWay headWay = new HeadWay();
        headWay.setId(source.getId());
        headWay.setDeck(source.getDeck());
        headWay.setProgress(source.getProgress());
        if (headWayRepository.getHeadById(source.getId()) != null) {
            HeadWay headWay1 = headWayRepository.getHeadById(source.getId());
            if (headWay1.getReports() != null) {
                headWay.setReports(headWay1.getReports());
            }
            if(headWay1.getDeck() != null){
                headWay.setDeck(headWay1.getDeck());
            }
            if (headWay1.getProgress() != null){
                headWay.setProgress(headWay1.getProgress());
            }
        }
        if(source.isDivided() == true){
            headWay.setDivided(true);
        }
        headWay.setWorking(source.isWorking());
        if (source.getR150() == null) {
            headWay.setR150(null);
        } else {
            headWay.setR150(source.getR150());
        }
        if (source.getCabinet() == null) {
            headWay.setCabinet(null);
        } else {
            headWay.setCabinet(source.getCabinet());
        }
        if (source.isChange() == true) {
            if (source.getR150New() != null) {
                headWay.setR150(r150Repository.getById(source.getR150New()));
            }
        }
        if (source.isChange2() == true) {
            if (source.getCabinetNew() != null) {
                headWay.setCabinet(cabinetRepository.getById(source.getCabinetNew()));
            }
        }
        if (source.getEmployees() != null){
            source.getEmployees().stream().forEach(employee -> employee.addHeadway(headWay));
            source.getEmployees().stream().forEach(employee -> headWay.addEmployee(employee));
        }
        return headWay;
    }
}

