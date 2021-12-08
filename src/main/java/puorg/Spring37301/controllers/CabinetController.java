package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.CabinetCommand;
import puorg.Spring37301.converters.CabinetCommandToCabinet;
import puorg.Spring37301.model.Cabinet;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.R150;
import puorg.Spring37301.repositories.CabinetRepository;
import puorg.Spring37301.repositories.FuseRepository;
import puorg.Spring37301.repositories.HeadWayRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Set;


@Controller
public class CabinetController {
    private CabinetRepository cabinetRepository;
    private CabinetCommandToCabinet cabinetCommandToCabinet;
    private FuseRepository fuseRepository;
    private HeadWayRepository headWayRepository;

    public CabinetController(CabinetRepository cabinetRepository, CabinetCommandToCabinet cabinetCommandToCabinet,
                             FuseRepository fuseRepository, HeadWayRepository headWayRepository){
        this.cabinetRepository = cabinetRepository;
        this.cabinetCommandToCabinet = cabinetCommandToCabinet;
        this.fuseRepository = fuseRepository;
        this.headWayRepository = headWayRepository;
    }
    @RequestMapping(value = {"/cabinets", "/cabinet/list"})
    public String getCabinets(Model model){
        model.addAttribute("cabinets", cabinetRepository.findAll());
        return "headways/cabinet/list";
    }
    @GetMapping("/cabinet/new")
    public String newCabinet(Model model){
        model.addAttribute("cabinet", new CabinetCommand());
        model.addAttribute("fuses", fuseRepository.findAllByAssigned(false));
        return "headways/cabinet/addedit";
    }
    @PostMapping("/cabinet/new/save")
    public String saveOrUpdate(@ModelAttribute CabinetCommand cabinetCommand) {

        Optional<Cabinet> cabinetOptional = cabinetRepository.getCabinetById(cabinetCommand.getId());

        if (!cabinetOptional.isPresent()) {
            Cabinet cabinet = cabinetCommandToCabinet.convert(cabinetCommand);
            cabinetRepository.save(cabinet);
        }
        //TODO: error message to template
        return "redirect:/cabinet/list/";
    }
    @GetMapping
    @RequestMapping("/cabinet/{id}/edit")
    public String editCabinet(Model model, @PathVariable("id") Long id){
        model.addAttribute("fuses2", fuseRepository.findAllByAssigned(false));
        model.addAttribute("cabinet", cabinetRepository.findById(id).get());
        return "headways/cabinet/edit";
    }
    @PostMapping("/cabinet/edit/save")
    public String saveCabinetUpdate(@ModelAttribute CabinetCommand cabinetCommand) {
        Optional<Cabinet> cabinetOptional = cabinetRepository.getCabinetById(cabinetCommand.getId());

        if (cabinetOptional.isPresent()) {
            Cabinet cabinet = cabinetCommandToCabinet.convert(cabinetCommand);
            cabinetRepository.save(cabinet);
        }
        return "redirect:/cabinet/list/";
    }

    @RequestMapping("/cabinet/{id}/show")
    public String getCabinetDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("cabinet", cabinetRepository.findById(id).get());
        return "headways/cabinet/show";
    }
    @GetMapping("/cabinet/{id}/delete")
    public String CabinetDelete(@PathVariable("id") Long id) {
        Cabinet cabinet = cabinetRepository.getById(id);
        HeadWay headWay = headWayRepository.getHeadWayByCabinet(cabinet);
        headWay.setCabinet(null);
        Set<Fuse> fuses = cabinet.getFuses();
        fuses.stream().forEach(fuse -> fuse.setCabinetFuse(null));
        cabinetRepository.deleteById(id);
        fuses.stream().forEach(fuse -> fuse.setAssigned(false));
        fuseRepository.saveAll(fuses);
        return "redirect:/cabinet/list";
    }
}
