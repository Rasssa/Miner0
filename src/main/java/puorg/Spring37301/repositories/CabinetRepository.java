package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.HeadWay;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CabinetRepository extends CrudRepository<Cabinet, Long> {
    Optional<Cabinet> getCabinetById(Long id);
    Set<Cabinet> getCabinetsByHeadWay(HeadWay headWay);
    Cabinet getById(Long id);
}
