package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.EmployeeCommand;
import puorg.Spring37301.model.Employee;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.repositories.EmployeeRepository;
import puorg.Spring37301.repositories.HeadWayRepository;

import java.util.Optional;
import java.util.Set;

@Component
public class EmployeeCommandToEmployee implements Converter<EmployeeCommand, Employee> {

    private HeadWayRepository headWayRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeCommandToEmployee(HeadWayRepository headWayRepository, EmployeeRepository employeeRepository) {
        this.headWayRepository = headWayRepository;
        this.employeeRepository = employeeRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Employee convert(EmployeeCommand source) {
        if (source == null) {
            return null;
        }
        final Employee employee = new Employee();
        employee.setId(source.getId());
        Employee employee1 = employeeRepository.getEmployeePresentById(source.getId());
        if (employee1 != null){
            employee.setPresent(employee1.isPresent());
        }
        employee.setFirstName(source.getFirstName());
        employee.setLastName(source.getLastName());
        employee.setSquad(source.getSquad());
       /* if (source.getHeadWays() != null){
            source.getHeadWays().stream().forEach(headWay -> headWay.addEmployee(employee));
        }*/
        if (source.getHeadWays() != null) {
            Set<HeadWay> headWays = source.getHeadWays();
            headWays.stream().forEach(headWay -> headWay.addEmployee(employee));
            employee.setHeadWays(headWays);
        } else {
            employee.setHeadWays(null);
        }
        employee.setShift(source.getShift());
        return employee;
    }
}
