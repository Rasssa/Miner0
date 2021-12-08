package puorg.Spring37301.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import puorg.Spring37301.commands.EmployeeCommand;
import puorg.Spring37301.commands.ReportCommand;
import puorg.Spring37301.converters.EmployeeCommandToEmployee;
import puorg.Spring37301.model.Employee;
import puorg.Spring37301.model.HeadWay;
import puorg.Spring37301.model.Report;
import puorg.Spring37301.repositories.EmployeeRepository;
import puorg.Spring37301.repositories.HeadWayRepository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Controller
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    private HeadWayRepository headWayRepository;
    private EmployeeCommandToEmployee employeeCommandToEmployee;

    public EmployeeController(EmployeeRepository employeeRepository, HeadWayRepository headWayRepository, EmployeeCommandToEmployee employeeCommandToEmployee){
        this.employeeRepository = employeeRepository;
        this.headWayRepository = headWayRepository;
        this.employeeCommandToEmployee = employeeCommandToEmployee;
    }

    @GetMapping
    @RequestMapping(value = {"/employees" , "employee/list"})
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "headways/employees/list";
    }
    @GetMapping
    @RequestMapping(value = {"/employees/shift"})
    public String getEmployeesShift(Model model) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int hours = ts.getHours();
        switch (hours){
            case 6:
                model.addAttribute("employees",employeeRepository.getEmployeesByShift(1l));
                break;
            case 12:
                model.addAttribute("employees",employeeRepository.getEmployeesByShift(2l));
                break;
            case 18:
                model.addAttribute("employees",employeeRepository.getEmployeesByShift(3l));
                break;
            case 24:
                model.addAttribute("employees",employeeRepository.getEmployeesByShift(4l));
                break;
            default:
                model.addAttribute("employees", employeeRepository.findAll());
        }
        return "headways/employees/listShift";
    }
    @GetMapping
    @RequestMapping("/employee/new")
    public String newEmployee(Model model){
        model.addAttribute("employee", new EmployeeCommand());
        model.addAttribute("headWays", headWayRepository.findAll());
        return "headways/employees/addedit";
    }
    @PostMapping("employee/new/save")
    public String saveOrUpdate(@ModelAttribute EmployeeCommand command){
        Employee detachedEmployee = employeeCommandToEmployee.convert(command);
        Employee savedEmployee = employeeRepository.save(detachedEmployee);
        employeeRepository.save(savedEmployee);

        return "redirect:/employee/list";
    }
    @GetMapping
    @RequestMapping("/employee/{id}/edit")
    public String editEmployee(Model model, @PathVariable("id") Long id){
        Employee employeeCommand = employeeRepository.getEmployeeById(id).get();
        employeeCommand.getHeadWays();
        model.addAttribute("employee", employeeCommand);
        model.addAttribute("headwaysAll", headWayRepository.findAll());

        return "headways/employees/edit";
    }
    @PostMapping("/employee/edit/save")
    public String saveEmployeeUpdate(@ModelAttribute EmployeeCommand employee) {
        Optional<Employee> employeeOptional = employeeRepository.getEmployeeById(employee.getId());

        if (employeeOptional.isPresent()) {
            Employee employee1 = employeeCommandToEmployee.convert(employee);
           employeeRepository.save(employee1);

        }
        return "redirect:/employee/list/";
    }
    @GetMapping("/employee/{id}/delete")
    public String refuelingDelete(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee/list";
    }
    @RequestMapping("/employee/{id}/show")
    public String getPublisherDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "headways/employees/show";
    }
    /*@GetMapping("employee/{id}/present")
    public String newDivide(Model model, @PathVariable("id") Long id){
        Employee employee = employeeRepository.getEmployeePresentById(id);
        employee.setPresent(true);
        employeeRepository.save(employee);
        return "redirect:/employees/shift";
    }*/
    @GetMapping
    @RequestMapping(value = "/employee/{id}/present", method = RequestMethod.GET)
    public String rateHandler(HttpServletRequest request, @PathVariable("id") Long id) {
        Employee employee = employeeRepository.getEmployeePresentById(id);
        employee.setPresent(true);
        employeeRepository.save(employee);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
    @GetMapping
    @RequestMapping(value = "/employee/{id}/absent", method = RequestMethod.GET)
    public String EmployeAbsent(HttpServletRequest request, @PathVariable("id") Long id) {
        Employee employee = employeeRepository.getEmployeePresentById(id);
        employee.setPresent(false);
        employeeRepository.save(employee);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
