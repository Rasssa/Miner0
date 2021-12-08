package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.CabinetCommand;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.Fuse;
import java.util.*;

@Component
public class CabinetCommandToCabinet implements Converter<CabinetCommand, Cabinet> {


    @Synchronized
    @Nullable
    @Override
    public Cabinet convert(CabinetCommand source){
        if (source == null){
            return null;
        }
        final Cabinet cabinet = new Cabinet();
        cabinet.setId(source.getId());
        cabinet.setTraffo1(source.getTraffo1());
        cabinet.setTraffo2(source.getTraffo2());
        cabinet.setType(source.getType());
        cabinet.setIdNumber(source.getIdNumber());
        Set<Fuse> fusesnow = source.getFuses();
        if (source.getFuses() != null) {
            Set<Fuse> fuses = source.getFuses();
            fuses.forEach(fuse -> cabinet.addFuse(fuse));
            fuses.forEach(fuse -> fuse.setAssigned(true));
            fuses.stream().filter(fuse -> fuse.isAssigned() == true).forEach(fuse -> fuse.setCabinetFuse(cabinet));
            cabinet.getFuses().addAll(fuses);
        }
        cabinet.getFuses().addAll(fusesnow);


        return cabinet;



    }
}
