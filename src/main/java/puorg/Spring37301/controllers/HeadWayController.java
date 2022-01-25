package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.HeadwayCommand;
import puorg.Spring37301.commands.ReportCommand;
import puorg.Spring37301.converters.HeadwayCommandToHeadway;
import puorg.Spring37301.converters.ReportCommandToReport;
import puorg.Spring37301.model.*;
import puorg.Spring37301.repositories.*;


import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HeadWayController {
    private HeadWayRepository headWayRepository;
    private HeadwayCommandToHeadway headwayCommandToHeadway;
    private EmployeeRepository employeeRepository;
    private R150Repository r150Repository;
    private CabinetRepository cabinetRepository;
    private ReportRepository reportRepository;
    private MaintenanceRepository maintenanceRepository;
    private ReportCommandToReport reportCommandToReport;
    private FuseRepository fuseRepository;

    public HeadWayController(HeadWayRepository headWayRepository, HeadwayCommandToHeadway headwayCommandToHeadway,
                             EmployeeRepository employeeRepository, R150Repository r150Repository,
                             CabinetRepository cabinetRepository, ReportRepository reportRepository,
                             MaintenanceRepository maintenanceRepository, ReportCommandToReport reportCommandToReport,
                             FuseRepository fuseRepository) {
        this.headWayRepository = headWayRepository;
        this.headwayCommandToHeadway = headwayCommandToHeadway;
        this.employeeRepository = employeeRepository;
        this.r150Repository = r150Repository;
        this.cabinetRepository = cabinetRepository;
        this.reportRepository = reportRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.reportCommandToReport = reportCommandToReport;
        this.fuseRepository = fuseRepository;

    }
    @GetMapping
    @RequestMapping(value = {"/headway/{id}/report"})
    public String SetHeadwayReport(Model model,  @PathVariable("id") Long id) {
        Set<Fuse> fusesExpired = fuseRepository.findAllByFuseExpired(true);
        Set<Fuse> fusesHead = new HashSet<>();
        HeadWay headWay = headWayRepository.getHeadById(id);
        if (headWay.getCabinet() != null || headWay.getR150() != null) {
            Set<Fuse> fusesR150 = fusesExpired.stream().filter(fuse -> fuse.getR150Fuse() == headWay.getR150()).collect(Collectors.toSet());
            Set<Fuse> fusesCabinet = fusesExpired.stream().filter(fuse -> fuse.getCabinetFuse() == headWay.getCabinet()).collect(Collectors.toSet());
            fusesHead.addAll(fusesR150);
            fusesHead.addAll(fusesCabinet);
        }
        model.addAttribute("fusesheadway", fusesHead);
        model.addAttribute("headway", headWayRepository.getHeadById(id));
        return "headways/headways/show";
    }

    @GetMapping
    @RequestMapping(value = {"/headways", "/headway/list", "/"})
    public String getHeadways(Model model) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.util.Date date = ts;
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Set<Fuse> fuses =  fuseRepository.findAllByAssignedAndExpire(true,sqlDate);
        fuses.stream().forEach(fuse -> fuse.setFuseExpired(true));
        fuseRepository.saveAll(fuses);
        Set<Fuse> fusesExpired = fuseRepository.findAllByFuseExpired(true);
        model.addAttribute("fusestoday", fusesExpired);
        model.addAttribute("headways", headWayRepository.findAll());
        return "headways/headways/list";
    }
    @GetMapping
    @RequestMapping(value = {"/headways/divide"})
    public String getHeadwaysDivide(Model model) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.util.Date date = ts;
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Set<Fuse> fuses =  fuseRepository.findAllByAssignedAndExpire(true,sqlDate);
        fuses.stream().forEach(fuse -> fuse.setFuseExpired(true));
        fuseRepository.saveAll(fuses);
        Set<Fuse> fusesExpired = fuseRepository.findAllByFuseExpired(true);
        model.addAttribute("fusestoday", fusesExpired);
        model.addAttribute("headways", headWayRepository.getHeadwaysByDivided(false));
        return "headways/headways/dividelist";
    }

    @GetMapping("/headway/new")
    public String newHeadWay(Model model) {
        model.addAttribute("headway", new HeadwayCommand());
        model.addAttribute("r150s", r150Repository.getR150sByHeadWay(null));
        model.addAttribute("cabinets", cabinetRepository.getCabinetsByHeadWay(null));

        return "headways/headways/addedit";
    }

    @PostMapping("/headway/new/save")
    public String saveOrUpdate(@ModelAttribute HeadwayCommand headwayCommand) {

        Optional<HeadWay> headWayOptional = headWayRepository.getHeadWayByDeck(headwayCommand.getDeck());
        HeadWay headWay = headwayCommandToHeadway.convert(headwayCommand);
        if (!headWayOptional.isPresent()) {

            headWayRepository.save(headWay);

        } else {

            headWayRepository.save(headWay);
        }
        //TODO: error message to template
        return "redirect:/headway/list";
    }

    @RequestMapping("/headway/{id}/employees")
    public String getHeadWayEmployees(Model model, @PathVariable("id") Long id) {
        Optional<HeadWay> headWay = headWayRepository.findById(id);

        if (headWay.isPresent()) {
            model.addAttribute("employees", employeeRepository.getAllByHeadWaysIsContaining(headWay.get()));
            model.addAttribute("filter", "headWay: " + headWay.get().getDeck());
        } else {
            model.addAttribute("employees", new ArrayList<>());
            model.addAttribute("filter", "artist for this id doesn't exist");
        }

        return "headways/employees/list";
    }

    @RequestMapping("/headway/{id}/r150s")
    public String getR150Details(Model model, @PathVariable("id") Long id) {
        model.addAttribute("r150", r150Repository.findById(id).get());
        return "headways/r150/show";
    }

    @GetMapping
    @RequestMapping("/headway/{id}/edit")
    public String editEmployee(Model model, @PathVariable("id") Long id) {
        model.addAttribute("headway", headWayRepository.findById(id).get());
        model.addAttribute("r150assigned", r150Repository.getR150sByHeadWay(headWayRepository.findById(id).get()));
        model.addAttribute("cabinetassigned", cabinetRepository.getCabinetsByHeadWay(headWayRepository.findById(id).get()));
        model.addAttribute("r150s", r150Repository.getR150sByHeadWay(null));
        model.addAttribute("cabinets", cabinetRepository.getCabinetsByHeadWay(null));;

        return "headways/headways/edit";
    }

    @PostMapping("/headway/edit/save")
    public String saveHeadwayUpdate(@ModelAttribute HeadwayCommand headwayCommand) {
        Optional<HeadWay> headWayOptional = headWayRepository.getHeadWayById(headwayCommand.getId());

        if (headWayOptional.isPresent()) {
            HeadWay headWay = headwayCommandToHeadway.convert(headwayCommand);
            headWayRepository.save(headWay);
        }
            return "redirect:/headway/list/";
        }
    @GetMapping("/headway/{id}/delete")
    public String HeadWayDelete(@PathVariable("id") Long id) {
        HeadWay headWay = headWayRepository.getHeadById(id);
        headWay.setR150(null);
        headWay.setCabinet(null);
        headWayRepository.deleteById(id);
        return "redirect:/headway/list";
    }

    @GetMapping("headway/{id}/report/new")
    public String newReport(Model model, @PathVariable("id") Long id){
        model.addAttribute("report", new ReportCommand());
        model.addAttribute("maintenans", maintenanceRepository.findAll());
        model.addAttribute("headwayy", headWayRepository.findById(id).get());
        return "headways/headways/report";
    }

    @GetMapping
    @RequestMapping(value = "/headway/{id}/stop", method = RequestMethod.GET)
    public String rateHandler(HttpServletRequest request,@PathVariable("id") Long id) {
        HeadWay headWay = headWayRepository.getHeadById(id);
        if (headWay.isWorking() == true){
            headWay.setWorking(false);
        }else {
            headWay.setWorking(true);
        }
        headWayRepository.save(headWay);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
    @PostMapping("headway/report/new/save")
    public String saveReport(@ModelAttribute ReportCommand reportCommand) {

        Optional<Report> reportOptional = reportRepository.getReportById(reportCommand.getId());

        if (!reportOptional.isPresent()) {
            Report report = reportCommandToReport.convert(reportCommand);
            reportRepository.save(report);
        }
        //TODO: error message to template
        return "redirect:/headway/" + reportCommand.getHeadWay().getId() + "/report";
    }
    @GetMapping("headway/{id}/divide")
    public String newDivide(Model model, @PathVariable("id") Long id){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int hours = ts.getHours();
        switch (hours){
            case 6:
                model.addAttribute("employees",employeeRepository.getEmployeesByShiftAndPresent(1l, true));
                break;
            case 12:
                model.addAttribute("employees",employeeRepository.getEmployeesByShiftAndPresent(2l,true));
                break;
            case 18:
                model.addAttribute("employees",employeeRepository.getEmployeesByShiftAndPresent(3l, true));
                break;
            case 24:
                model.addAttribute("employees",employeeRepository.getEmployeesByShiftAndPresent(4l, true));
                break;
            default:
                model.addAttribute("employees",employeeRepository.findAll());
        }
        model.addAttribute("headwayy", headWayRepository.findById(id).get());
        model.addAttribute("r150assigned", r150Repository.getR150sByHeadWay(headWayRepository.findById(id).get()));
        model.addAttribute("cabinetassigned", cabinetRepository.getCabinetsByHeadWay(headWayRepository.findById(id).get()));
        return "headways/headways/divide";
    }
    @PostMapping("headway/divide/new/save")
    public String saveDivide(@ModelAttribute HeadwayCommand headwayCommand) {

        Optional<HeadWay> headWayOptional = headWayRepository.getHeadWayById(headwayCommand.getId());

        if (headWayOptional.isPresent()) {
            HeadWay headWay1 = headWayRepository.getHeadById(headwayCommand.getId());
            Set<Employee> employees = headWay1.getEmployees();
            employees.stream().forEach(employee -> employee.setHeadWays(null));
            headWay1.setEmployees(null);
            headWayRepository.save(headWay1);
            HeadWay headWay = headwayCommandToHeadway.convert(headwayCommand);
            headWay.setWorking(headWay1.isWorking());
            headWayRepository.save(headWay);
        }
        return "redirect:/headways/divide";
    }
    @GetMapping
    @RequestMapping(value = {"/headway/divide/false"})
    public String newDivide(Model model) {
        Set<HeadWay> headWays = headWayRepository.getHeadwaysByDivided(true);
        headWays.stream().forEach(headWay -> headWay.setDivided(false));
        Iterable<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> employee.setFilter(false));
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int hours = ts.getHours();
        switch (hours){
            case 6:
                headWays.stream().forEach(headWay -> headWay.getEmployees()
                        .stream().filter(employee -> employee.getShift() == 1)
                        .forEach(employee -> employee.setFilter(true)));
                break;
            case 12:
                headWays.stream().forEach(headWay -> headWay.getEmployees()
                        .stream().filter(employee -> employee.getShift() == 2)
                        .forEach(employee -> employee.setFilter(true)));
                break;
            case 18:
                headWays.stream().forEach(headWay -> headWay.getEmployees()
                        .stream().filter(employee -> employee.getShift() == 3)
                        .forEach(employee -> employee.setFilter(true)));
                break;
            case 24:
                headWays.stream().forEach(headWay -> headWay.getEmployees()
                        .stream().filter(employee -> employee.getShift() == 4)
                        .forEach(employee -> employee.setFilter(true)));
                break;
            default:
                headWays.stream().forEach(headWay -> headWay.getEmployees()
                        .stream().forEach(employee -> employee.setFilter(true)));
        }

        headWayRepository.saveAll(headWays);
        return "redirect:/headways/divide";
    }

}

