package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.R150;
import java.util.Optional;
import java.util.Set;

@Repository
public interface R150Repository extends CrudRepository<R150, Long> {

    Optional<R150> getR150ById(Long id);
    Set<R150> getR150sByHeadWay(HeadWay headWay);
    R150 getById(Long id);
    Set<R150> findAllByHeadWay(HeadWay headWay);
}
