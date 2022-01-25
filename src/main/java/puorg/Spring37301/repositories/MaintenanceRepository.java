package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import puorg.Spring37301.model.Maintenance;

import java.util.Optional;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {
    Maintenance getMainteById(Long id);
    Optional<Maintenance> getMaintenanceById(Long id);
}
