package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.CabinetCommand;
import puorg.Spring37301.commands.MaintenanceCommand;
import puorg.Spring37301.commands.ReportCommand;
import puorg.Spring37301.converters.CabinetCommandToCabinet;
import puorg.Spring37301.converters.ReportCommandToReport;
import puorg.Spring37301.model.*;
import puorg.Spring37301.repositories.HeadWayRepository;
import puorg.Spring37301.repositories.MaintenanceRepository;
import puorg.Spring37301.repositories.ReportRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ReportController {
    private MaintenanceRepository maintenanceRepository;
    private ReportRepository reportRepository;
    private ReportCommandToReport reportCommandToReport;
    private HeadWayRepository headWayRepository;

    public ReportController(MaintenanceRepository maintenanceRepository, ReportRepository reportRepository,
                            ReportCommandToReport reportCommandToReport, HeadWayRepository headWayRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.reportRepository = reportRepository;
        this.reportCommandToReport = reportCommandToReport;
        this.headWayRepository = headWayRepository;
    }
    @GetMapping("/report/new")
    public String newReport(Model model){
        model.addAttribute("report", new ReportCommand());
        model.addAttribute("maintenans", maintenanceRepository.findAll());
        /*model.addAttribute("headway", headWayRepository.findById(re) )*/
        return "headways/report/add";
    }
    @PostMapping("/report/new/save")
    public String saveOrUpdate(@ModelAttribute ReportCommand reportCommand) {

        Optional<Report> reportOptional = reportRepository.getReportById(reportCommand.getId());


        if (!reportOptional.isPresent()) {
            Report report = reportCommandToReport.convert(reportCommand);
            reportRepository.save(report);

        }
        //TODO: error message to template
        return "redirect:/report/new";
    }

    @RequestMapping(value = {"/reports", "/report/list"})
    public String getReports(Model model){
        model.addAttribute("reports", reportRepository.findAll());

        return "headways/report/list";
    }
    @GetMapping("/headway/{id2}/report/{id}/done")
    public String ReportComplete(@PathVariable("id") Long id) {
        Report report = reportRepository.getReportUpById(id);
        report.setDescription(null);
        reportRepository.save(report);
        return "redirect:/headways";
    }
    @GetMapping
    @RequestMapping("/report/{id}/edit")
    public String editReport(Model model, @PathVariable("id") Long id){
        Report report = reportRepository.getReportUpById(id);
        HeadWay headWay = report.getHeadWay();
        model.addAttribute("report", reportRepository.findById(id).get());
        model.addAttribute("maintenans", maintenanceRepository.findAll());
        model.addAttribute("headwayy", headWay);
        return "headways/report/edit";
    }

    @GetMapping
    @RequestMapping(value = "/report/edit/save", method = RequestMethod.POST)
    public String rateHandler(HttpServletRequest request, @ModelAttribute ReportCommand reportCommand) {
        Optional<Report> reportOptional = reportRepository.getReportById(reportCommand.getId());

        if (reportOptional.isPresent()) {
            Report report = reportCommandToReport.convert(reportCommand);
            reportRepository.save(report);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

   /* @PostMapping("/report/edit/save")
    public String saveReportUpdate(@ModelAttribute ReportCommand reportCommand) {
        Optional<Report> reportOptional = reportRepository.getReportById(reportCommand.getId());

        if (reportOptional.isPresent()) {
                Report report = reportCommandToReport.convert(reportCommand);
                reportRepository.save(report);
        }
        return "redirect:/headways/";
    }*/

   /* @GetMapping("/report/{id2}/maintenance/{id}/delete")
    public String r150Delete(@PathVariable("id") Long id, @PathVariable("id") Long id2) {
        Maintenance maintenance = maintenanceRepository.getMainteById(id);
        maintenance.setReport(null);
        maintenanceRepository.save(maintenance);
        return "redirect:/headway";
    }*/

    @GetMapping
    @RequestMapping(value = "/report/{id2}/maintenance/{id}/delete", method = RequestMethod.GET)
    public String rateHandler(HttpServletRequest request, @PathVariable("id") Long id) {
        Maintenance maintenance = maintenanceRepository.getMainteById(id);
        maintenance.setReport(null);
        maintenanceRepository.save(maintenance);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/report/{id}/delete")
    public String reportDelete(@PathVariable("id") Long id) {
        reportRepository.deleteById(id);
        return "redirect:/headways";
    }
}
