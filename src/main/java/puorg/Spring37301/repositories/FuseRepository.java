package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.model.R150;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface FuseRepository extends CrudRepository<Fuse, Long> {

    Optional<Fuse> getFuseById(Long id);
    Set<Fuse> findAllByAssigned(boolean assigned);
    Set<Fuse> findAllByAssignedAndExpire(boolean assigned, Date expire);
    Set<Fuse> findAllByFuseExpired(boolean FuseExpired);
    Fuse findFuseUpById(Long id);

   /* Set<Fuse> findAllByExpire(Date expire);*/
    /*Set<Fuse> findAllByCabinet(Cabinet cabinet);*/
    /*Timestamp ts = new Timestamp(System.currentTimeMillis());
    java.util.Date date = ts;
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    model.addAttribute("fuses", fuseRepository.findAllByExpire(sqlDate));*/

}

