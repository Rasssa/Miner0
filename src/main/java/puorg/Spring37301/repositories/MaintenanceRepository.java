package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import puorg.Spring37301.model.Maintenance;

import java.util.Optional;

public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {
    Maintenance getMainteById(Long id);
    Optional<Maintenance> getMaintenanceById(Long id);
}
