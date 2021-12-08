package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.ReportCommand;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.Maintenance;
import puorg.Spring37301.model.Report;
import puorg.Spring37301.repositories.*;

import java.util.HashSet;
import java.util.Set;


@Component
public class ReportCommandToReport implements Converter<ReportCommand, Report> {

    private ReportRepository reportRepository;

    public ReportCommandToReport(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Report convert(ReportCommand source){
        if (source == null){
            return null;
        }
        final Report report = new Report();
        report.setId(source.getId());
        report.setDescription(source.getDescription());
        report.setShift(source.getShift());
        Report report1 = reportRepository.getReportUpById(source.getId());
        if (report1 != null){
            report.setMaintenances(report1.getMaintenances());
        }
        if (source.getMaintenances() != null) {
            Set<Maintenance> maintenances = source.getMaintenances();
            maintenances.forEach(maintenance -> report.addMaintenance(maintenance));
            maintenances.stream().forEach(maintenance -> maintenance.addReport(report));
            source.getMaintenances().addAll(maintenances);
        }
        if (source.getHeadWay() != null) {
            report.setHeadWay(source.getHeadWay());
            HeadWay headWay = source.getHeadWay();
            headWay.setReports(report);
            if(source.isWorking() == true) {
                headWay.setWorking(true);
            } else {
                headWay.setWorking(false);
            }
        } else {
            report.setHeadWay(report.getHeadWay());
        }
        return report;

    }
}

