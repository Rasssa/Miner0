package puorg.Spring37301.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import puorg.Spring37301.commands.FuseCommand;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.repositories.CabinetRepository;


@Component
public class FuseCommandToFuse implements Converter<FuseCommand, Fuse> {


    @Synchronized
    @Nullable
    @Override
    public Fuse convert(FuseCommand source){

        if (source == null) {
            return null;
        }
        final Fuse fuse = new Fuse();
        fuse.setId(source.getId());
        if (source.isFuseExpired() == true) {
            fuse.setFuseExpired(true);
        }else {
            fuse.setFuseExpired(false);
        }
        fuse.setExpire(source.getExpire());
        fuse.setName(source.getName());
        fuse.setSetting(source.getSetting());
        fuse.setIdNumber(source.getIdNumber());
        if (fuse.getR150Fuse() == null){
            fuse.setR150Fuse(null);
        }
        if (fuse.getCabinetFuse() == null){
            fuse.setCabinetFuse(null);
        }

        return fuse;
    }
}