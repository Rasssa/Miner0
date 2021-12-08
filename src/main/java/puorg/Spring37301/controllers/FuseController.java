package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.CabinetCommand;
import puorg.Spring37301.commands.FuseCommand;
import puorg.Spring37301.converters.CabinetCommandToCabinet;
import puorg.Spring37301.converters.FuseCommandToFuse;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.repositories.CabinetRepository;
import puorg.Spring37301.repositories.FuseRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class FuseController {

    private FuseRepository fuseRepository;
    private FuseCommandToFuse fuseCommandToFuse;
    private CabinetRepository cabinetRepository;

    public FuseController(FuseRepository fuseRepository, FuseCommandToFuse fuseCommandToFuse, CabinetRepository cabinetRepository){
        this.fuseRepository = fuseRepository;
        this.fuseCommandToFuse = fuseCommandToFuse;
        this.cabinetRepository = cabinetRepository;
    }

    @GetMapping("/fuse/new")
    public String newFuse(Model model){
        model.addAttribute("fuse", new FuseCommand());
        return "headways/fuse/add";
    }
    @PostMapping("/fuse/new/save")
    public String saveOrUpdate(@ModelAttribute FuseCommand fuse) {

        Optional<Fuse> fuseOptional = fuseRepository.getFuseById(fuse.getId());

        if (!fuseOptional.isPresent()) {
            Fuse fuse1 = fuseCommandToFuse.convert(fuse);
            fuseRepository.save(fuse1);

        }
        //TODO: error message to template
        return "redirect:/fuses";
    }
    @RequestMapping(value = {"/fuses", "/fuse/list"})
    public String getCabinets(Model model){
        model.addAttribute("fuses", fuseRepository.findAll());

        return "headways/fuse/list";
    }

    @GetMapping("/headway/fuse/{id}/delete")
    public String HeadWayDelete(@PathVariable("id") Long id) {
        Fuse fuse = fuseRepository.findFuseUpById(id);
        fuse.setCabinetFuse(null);
        fuse.setR150Fuse(null);
        fuse.setAssigned(false);
        fuseRepository.save(fuse);
        return "redirect:/headway/list";
    }
    @GetMapping("/r150/{id2}/fuse/{id}/delete")
    public String r150FuseDelete(@PathVariable("id") Long id, @PathVariable("id") Long id2) {
        Fuse fuse = fuseRepository.findFuseUpById(id);
        fuse.setCabinetFuse(null);
        fuse.setR150Fuse(null);
        fuse.setAssigned(false);
        fuseRepository.save(fuse);
        return "redirect:/r150/{id2}/edit";
    }
    @GetMapping("/cabinet/{id2}/fuse/{id}/delete")
    public String cabinetFuseDelete(@PathVariable("id") Long id) {
        Fuse fuse = fuseRepository.findFuseUpById(id);
        fuse.setCabinetFuse(null);
        fuse.setR150Fuse(null);
        fuse.setAssigned(false);
        fuseRepository.save(fuse);
        return "redirect:/cabinet/{id2}/edit";
    }

    @RequestMapping(value = {"/fuses/today"})
    public String getTodayFuses(Model model){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.util.Date date = ts;
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        model.addAttribute("fuses", fuseRepository.findAllByAssignedAndExpire(true,sqlDate));

        return "headways/fuse/list";
    }
    @GetMapping
    @RequestMapping("/fuse/{id}/edit")
    public String editFuse(Model model, @PathVariable("id") Long id){
        model.addAttribute("fuse", fuseRepository.findById(id).get());
        return "headways/fuse/editfuse";
    }

    @PostMapping("/fuse/edit/save")
    public String saveEditFuseUpdate(@ModelAttribute FuseCommand fuseCommand) {
        Optional<Fuse> fuseOptional = fuseRepository.getFuseById(fuseCommand.getId());

        if (fuseOptional.isPresent()) {
            Fuse fusehelp = fuseRepository.findFuseUpById(fuseCommand.getId());
            Fuse fuse = fuseCommandToFuse.convert(fuseCommand);
            fuse.setR150Fuse(fusehelp.getR150Fuse());
            fuse.setCabinetFuse(fusehelp.getCabinetFuse());
            fuse.setAssigned(fusehelp.isAssigned());
            fuse.setName(fusehelp.getName());
            fuseRepository.save(fuse);
        }
        return "redirect:/fuse/list/";
    }

    @GetMapping("/fuse/{id}/delete")
    public String FuseDelete(@PathVariable("id") Long id) {
        fuseRepository.deleteById(id);
        return "redirect:/fuses";
    }
    @RequestMapping("/fuse/{id}/show")
    public String getFuseDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("fuse", fuseRepository.findFuseUpById(id));
        return "headways/fuse/show";
    }
}
