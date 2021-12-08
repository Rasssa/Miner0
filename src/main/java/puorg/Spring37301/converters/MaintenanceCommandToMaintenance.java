package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.HeadwayCommand;
import puorg.Spring37301.commands.MaintenanceCommand;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.Maintenance;
import puorg.Spring37301.repositories.MaintenanceRepository;

@Component
public class MaintenanceCommandToMaintenance implements Converter<MaintenanceCommand, Maintenance> {
    private MaintenanceRepository maintenanceRepository;

    public MaintenanceCommandToMaintenance(MaintenanceRepository maintenanceRepository){
        this.maintenanceRepository = maintenanceRepository;
    }
    @Synchronized
    @Nullable
    @Override
    public Maintenance convert(MaintenanceCommand source){
        if (source ==null){
            return null;
        }
        final Maintenance maintenance = new Maintenance();
        maintenance.setId(source.getId());
        Maintenance maintenance1 = maintenanceRepository.getMainteById(source.getId());
        if (maintenance1 != null){
            maintenance1.setDescription(source.getDescription());
            return maintenance1;
        } else {
            maintenance.setName(source.getName());
            maintenance.setDescription(source.getDescription());
            maintenance.setTimeForMaintenance(source.getTimeForMaintenance());
            maintenance.setCountOfIncidents(source.getCountOfIncidents());
            maintenance.setEmployesOrder(source.getEmployesOrder());
            return maintenance;
        }
    }
}
