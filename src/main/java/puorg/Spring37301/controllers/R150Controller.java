package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.R150Command;
import puorg.Spring37301.converters.R150CommandToR150;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.R150;
import puorg.Spring37301.repositories.FuseRepository;
import puorg.Spring37301.repositories.HeadWayRepository;
import puorg.Spring37301.repositories.R150Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Controller
public class R150Controller {
    private R150Repository r150Repository;
    private R150CommandToR150 r150CommandToR150;
    private HeadWayRepository headWayRepository;
    private FuseRepository fuseRepository;

    public R150Controller(R150Repository r150Repository, R150CommandToR150 r150CommandToR150, HeadWayRepository headWayRepository, FuseRepository fuseRepository){
        this.r150Repository = r150Repository;
        this.r150CommandToR150 = r150CommandToR150;
        this.headWayRepository = headWayRepository;
        this.fuseRepository = fuseRepository;
    }
    @RequestMapping(value = {"/r150s", "/r150/list"})
    public String getR150s(Model model){
        model.addAttribute("r150s", r150Repository.findAll());
        return "headways/r150/list";
    }
    @GetMapping("/r150/new")
    public String newR150(Model model){
        model.addAttribute("r150", new R150Command());
        model.addAttribute("fuses", fuseRepository.findAllByAssigned(false));
        return "headways/r150/addedit";
    }
    @PostMapping("/r150/new/save")
    public String saveOrUpdate(@ModelAttribute R150Command r150Command) {

        Optional<R150> r150Optional = r150Repository.getR150ById(r150Command.getId());

        if (!r150Optional.isPresent()) {
            R150 r150 = r150CommandToR150.convert(r150Command);
            r150Repository.save(r150);

        }
        //TODO: error message to template
        return "redirect:/r150/list/";
    }
    @RequestMapping("/r150/{id}/show")
    public String getR150Details(Model model, @PathVariable("id") Long id) {
        model.addAttribute("r150", r150Repository.findById(id).get());
        return "headways/r150/show";
    }
    @GetMapping
    @RequestMapping("/r150/{id}/edit")
    public String editR150(Model model, @PathVariable("id") Long id){
        model.addAttribute("fuses2", fuseRepository.findAllByAssigned(false));
        model.addAttribute("r150", r150Repository.findById(id).get());
        return "headways/r150/edit";
    }
    @PostMapping("/r150/edit/save")
    public String saveR150Update(@ModelAttribute R150Command r150Command) {
        Optional<R150> r150Optional = r150Repository.getR150ById(r150Command.getId());

        if (r150Optional.isPresent()) {
            R150 r150 = r150CommandToR150.convert(r150Command);
            r150Repository.save(r150);
        }
        return "redirect:/r150/list/";
    }
    @GetMapping("/r150/{id}/delete")
    public String r150Delete(@PathVariable("id") Long id) {
        R150 r150 = r150Repository.getById(id);
        HeadWay headWay = headWayRepository.getHeadWayByR150(r150);
        headWay.setR150(null);
        Set<Fuse> fuses = r150.getFuses();
        fuses.stream().forEach(fuse -> fuse.setR150Fuse(null));
        r150Repository.deleteById(id);
        fuses.stream().forEach(fuse -> fuse.setAssigned(false));
        fuseRepository.saveAll(fuses);
        return "redirect:/r150/list";
    }
}
