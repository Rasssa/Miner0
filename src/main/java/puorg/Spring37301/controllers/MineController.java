package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MineController {
    @RequestMapping(value = {"/"})
    public String getHeadWays() {
        return "headways/headways/list";
    }
}
