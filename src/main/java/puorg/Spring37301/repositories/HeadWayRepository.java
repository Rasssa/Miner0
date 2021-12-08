package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.R150;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HeadWayRepository extends CrudRepository<HeadWay, Long> {
    Optional<HeadWay> getHeadWayByDeck(String deck);
    Optional<HeadWay> getHeadWayById(Long id);
    HeadWay getHeadById (Long id);
    HeadWay getHeadWayByR150(R150 r150);
    HeadWay getHeadWayByCabinet(Cabinet cabinet);
    Set<HeadWay> getHeadwaysByDivided(boolean divided);
}
