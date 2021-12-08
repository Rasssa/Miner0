package puorg.Spring37301.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import puorg.Spring37301.model.Report;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    Optional<Report> getReportById(Long id);
    Report getReportUpById(Long id);
}