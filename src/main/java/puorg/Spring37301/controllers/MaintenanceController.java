package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.EmployeeCommand;
import puorg.Spring37301.commands.FuseCommand;
import puorg.Spring37301.commands.MaintenanceCommand;
import puorg.Spring37301.converters.EmployeeCommandToEmployee;
import puorg.Spring37301.converters.MaintenanceCommandToMaintenance;
import puorg.Spring37301.model.Employee;
import puorg.Spring37301.model.Fuse;
import puorg.Spring37301.model.Maintenance;
import puorg.Spring37301.repositories.EmployeeRepository;
import puorg.Spring37301.repositories.HeadWayRepository;
import puorg.Spring37301.repositories.MaintenanceRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MaintenanceController {
    private MaintenanceRepository maintenanceRepository;
    private MaintenanceCommandToMaintenance maintenanceCommandToMaintenance;

    public MaintenanceController(MaintenanceRepository maintenanceRepository,
                                 MaintenanceCommandToMaintenance maintenanceCommandToMaintenance){
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceCommandToMaintenance = maintenanceCommandToMaintenance;
    }
    @GetMapping("/maintenance/new")
    public String newMaintenance(Model model){
        model.addAttribute("maintenance", new MaintenanceCommand());
        return "headways/maintenance/add";
    }
    @PostMapping("/maintenance/new/save")
    public String saveOrUpdate(@ModelAttribute MaintenanceCommand maintenance) {

        Optional<Maintenance> maintenanceOptional = maintenanceRepository.getMaintenanceById(maintenance.getId());

        if (!maintenanceOptional.isPresent()) {
            Maintenance maintenance1 = maintenanceCommandToMaintenance.convert(maintenance);
            maintenanceRepository.save(maintenance1);

        }
        return "redirect:/headways";
    }
    @GetMapping
    @RequestMapping(value = {"/maintenances" , "maintenance/list"})
    public String getMaintenances(Model model) {
        model.addAttribute("employees", maintenanceRepository.findAll());
        return "headways/maintenance/list";
    }


    @GetMapping
    @RequestMapping("/maintenance/{id}/edit")
    public String editMaintenance(Model model, @PathVariable("id") Long id){
        model.addAttribute("maintenance", maintenanceRepository.getMainteById(id));
        return "headways/maintenance/edit";
    }
    @GetMapping
    @RequestMapping(value = "/maintenance/edit/save", method = RequestMethod.POST)
    public String rateHandler(HttpServletRequest request,@ModelAttribute MaintenanceCommand maintenanceCommand) {
        Optional<Maintenance> maintenanceOptional = maintenanceRepository.getMaintenanceById(maintenanceCommand.getId());

        if (maintenanceOptional.isPresent()) {
            Maintenance maintenance = maintenanceCommandToMaintenance.convert(maintenanceCommand);
            maintenanceRepository.save(maintenance);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    /*@PostMapping("/maintenance/edit/save")
    public String saveEmployeeUpdate(@ModelAttribute MaintenanceCommand maintenanceCommand) {
        Optional<Maintenance> maintenanceOptional = maintenanceRepository.getMaintenanceById(maintenanceCommand.getId());

        if (maintenanceOptional.isPresent()) {
            Maintenance maintenance = maintenanceCommandToMaintenance.convert(maintenanceCommand);
            maintenanceRepository.save(maintenance);
        }
        return "redirect:/headway/list/";
    }*/
    @GetMapping("/maintenance/{id}/delete")
    public String maintenanceDelete(@PathVariable("id") Long id) {
        maintenanceRepository.deleteById(id);
        return "redirect:/maintenance/list";
    }
}
