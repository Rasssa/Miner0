package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import puorg.Spring37301.model.Employee;
import puorg.Spring37301.model.HeadWay;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> getAllByHeadWaysIsContaining(HeadWay headWay);
    Optional<Employee> getEmployeeById(Long id);
    Employee getEmployeePresentById(Long id);
    Set<Employee> getEmployeesByShift(Long shift);
    Set<Employee> getEmployeesByShiftAndPresent(Long shift, boolean present);

}
