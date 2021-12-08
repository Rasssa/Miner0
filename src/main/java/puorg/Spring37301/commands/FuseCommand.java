package puorg.Spring37301.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class FuseCommand {
    private Long id;
    private Date expire;
    private boolean fuseExpired;
    private String name;
    private Long setting;
    private String idNumber;

}
