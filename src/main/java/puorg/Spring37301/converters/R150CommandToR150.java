package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.R150Command;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.model.R150;
import java.util.Set;

@Component
public class R150CommandToR150 implements Converter<R150Command, R150> {

    @Synchronized
    @Nullable
    @Override
    public R150 convert(R150Command source) {
        if (source == null) {
            return null;
        }
        final R150 r150 = new R150();
        r150.setId(source.getId());
        r150.setElectricalCabinet(source.getElectricalCabinet());
        r150.setIdNumber(source.getIdNumber());
        if (source.getFuses() != null) {
            Set<Fuse> fuses = source.getFuses();
            fuses.forEach(fuse -> r150.addFuse(fuse));
            fuses.forEach(fuse -> fuse.setAssigned(true));
            fuses.stream().filter(fuse -> fuse.isAssigned() == true).forEach(fuse -> fuse.setR150Fuse(r150));
            r150.getFuses().addAll(fuses);
        }


        return r150;
    }
}
