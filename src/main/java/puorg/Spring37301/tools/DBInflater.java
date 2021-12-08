package puorg.Spring37301.tools;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import puorg.Spring37301.model.*;
import puorg.Spring37301.repositories.*;

import java.util.HashSet;
import java.util.Set;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    private MaintenanceRepository maintenanceRepository;
    private R150Repository r150Repository;
    private CabinetRepository cabinetRepository;
    private HeadWayRepository headWayRepository;
    private EmployeeRepository employeeRepository;
    private FuseRepository fuseRepository;
    private ReportRepository reportRepository;

    public DBInflater(CabinetRepository cabinetRepository, HeadWayRepository headWayRepository,
                      EmployeeRepository employeeRepository, FuseRepository fuseRepository,
                      MaintenanceRepository maintenanceRepository, R150Repository r150Repository,
                      ReportRepository reportRepository) {
        this.cabinetRepository = cabinetRepository;
        this.headWayRepository = headWayRepository;
        this.employeeRepository = employeeRepository;
        this.fuseRepository = fuseRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.r150Repository = r150Repository;
        this.reportRepository = reportRepository;
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
    private void initData() {
        Maintenance Wydłóżka1000v = new Maintenance(1l, "Przebudowa transformatora 1000v", "", 60l, 0l, 2l);
        Maintenance Wydłóżka500v = new Maintenance(2l, "Przebudowa transformatora 500v","", 60l, 0l, 2l);
        Maintenance Wydłóżka500Trafo = new Maintenance(3l, "Wydłóżka 500v Trafo","", 60l, 0l, 2l);
        Maintenance Wydłóżka1000Trafo = new Maintenance(4l, "Wydłóżka 1000v Trafo","", 60l, 0l, 2l);
        Maintenance CabinetMove = new Maintenance(5l, "Zrzucanie szafy manewrowej","", 60l, 0l, 2l);
        Maintenance R150Assemblage = new Maintenance(6l, "Montaż kombajnu","", 60l, 0l, 2l);
        Maintenance Mufa = new Maintenance(7l, "Mufa","", 60l, 0l, 2l);
        Maintenance Wydłóżka = new Maintenance(8l, "wydłózka","", 60l, 0l, 2l);

        maintenanceRepository.save(Wydłóżka);
        maintenanceRepository.save(Wydłóżka1000v);
        maintenanceRepository.save(Wydłóżka500v);
        maintenanceRepository.save(Wydłóżka500Trafo);
        maintenanceRepository.save(Wydłóżka1000Trafo);
        maintenanceRepository.save(CabinetMove);
        maintenanceRepository.save(R150Assemblage);
        maintenanceRepository.save(Mufa);

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        Fuse fuse1 = new Fuse(1l, date, "EHP-15", 110l,"2203/1");
        Fuse fuse2 = new Fuse(2l, date, "EHP-15", 110l,"2203/2");
        Fuse fuse3 = new Fuse(3l, date, "EHP-15", 110l,"2203/3");
        Fuse fuse4 = new Fuse(4l, date, "EHP-15", 110l,"2203/4");
        Fuse fuse5 = new Fuse(5l, date, "EHP-15", 110l,"2203/5");
        Fuse fuse6 = new Fuse(6l, date, "EHP-15", 110l, "2203/6");
        Fuse fuse7 = new Fuse(7l, date, "EHP-15", 110l,"2203/7");
        Fuse fuse8 = new Fuse(8l, date, "EHP-15", 110l,"2203/8");
        Fuse fuse9 = new Fuse(9l, date, "EHP-15", 110l,"2203/9");
        Fuse fuse10 = new Fuse(10l, date, "EHP-15", 110l,"2203/10");
        Fuse fuse11 = new Fuse(11l, date, "EHP-15", 110l,"2203/11");
        Fuse fuse12 = new Fuse(12l, date, "EHP-15", 110l, "2203/12");
        Fuse fuse13 = new Fuse(13l, date, "EHP-15", 110l,"2203/13");
        Fuse fuse14 = new Fuse(14l, date, "EHP-15", 110l,"2203/14");
        Fuse fuse15 = new Fuse(15l, date, "EHP-15", 110l,"2203/15");
        Fuse fuse16 = new Fuse(16l, date, "EHP-15", 110l,"2203/16");
        fuseRepository.save(fuse1);
        fuseRepository.save(fuse2);
        fuseRepository.save(fuse3);
        fuseRepository.save(fuse4);
        fuseRepository.save(fuse5);
        fuseRepository.save(fuse6);
        fuseRepository.save(fuse7);
        fuseRepository.save(fuse8);
        fuseRepository.save(fuse9);
        fuseRepository.save(fuse10);
        fuseRepository.save(fuse11);
        fuseRepository.save(fuse12);
        fuseRepository.save(fuse13);
        fuseRepository.save(fuse14);
        fuseRepository.save(fuse15);
        fuseRepository.save(fuse16);

        Set<Fuse> fuses = new HashSet<>();
        fuses.add(fuse1);
        Set<Fuse> fuses2 = new HashSet<>();
        fuses2.add(fuse2);
        Set<Fuse> fuses3 = new HashSet<>();
        fuses3.add(fuse3);
        Set<Fuse> fuses4 = new HashSet<>();
        fuses4.add(fuse4);
        Set<Fuse> fuses5 = new HashSet<>();
        fuses5.add(fuse5);
        Set<Fuse> fuses6 = new HashSet<>();
        fuses6.add(fuse6);

        R150 Ess = new R150(1l, "OSKK", "22/01",fuses);
        R150 Ess2 = new R150(2l, "OSKK", "22/02",fuses2);
        R150 Ess3 = new R150(3l, "OSKK", "22/03",fuses3);
        R150 Ess4 = new R150(4l, "OSKK", "22/04",fuses4);
        R150 Ess5 = new R150(5l, "OSKK", "22/05",fuses5);
        R150 Ess6 = new R150(6l, "OSKK", "22/06",fuses6);
        r150Repository.save(Ess);
        r150Repository.save(Ess2);
        r150Repository.save(Ess3);
        r150Repository.save(Ess4);
        r150Repository.save(Ess5);
        r150Repository.save(Ess6);
        fuse1.setR150Fuse(Ess);
        fuse1.setAssigned(true);
        fuse2.setR150Fuse(Ess2);
        fuse2.setAssigned(true);
        fuse3.setR150Fuse(Ess3);
        fuse3.setAssigned(true);
        fuse4.setR150Fuse(Ess4);
        fuse4.setAssigned(true);
        fuse5.setR150Fuse(Ess5);
        fuse5.setAssigned(true);
        fuse6.setR150Fuse(Ess6);
        fuse6.setAssigned(true);

        Set<Fuse> fuses7 = new HashSet<>();
        fuses7.add(fuse7);
        Set<Fuse> fuses8 = new HashSet<>();
        fuses8.add(fuse8);
        Set<Fuse> fuses9 = new HashSet<>();
        fuses9.add(fuse9);
        Set<Fuse> fuses10 = new HashSet<>();
        fuses10.add(fuse10);
        Set<Fuse> fuses11 = new HashSet<>();
        fuses11.add(fuse11);
        Set<Fuse> fuses12 = new HashSet<>();
        fuses12.add(fuse12);
        Cabinet cabinet = new Cabinet(1l, 202l, 303l, "EH-D2", "222/1",fuses7);
        Cabinet cabinet2 = new Cabinet(2l, 202l, 303l, "EH-D2", "222/2",fuses8);
        Cabinet cabinet3 = new Cabinet(3l, 202l, 303l, "EH-D2", "222/3",fuses9);
        Cabinet cabinet4 = new Cabinet(4l, 202l, 303l, "EH-D2", "222/4",fuses10);
        Cabinet cabinet5 = new Cabinet(5l, 202l, 303l, "EH-D2", "222/5",fuses11);
        Cabinet cabinet6 = new Cabinet(6l, 202l, 303l, "EH-D2", "222/6",fuses12);
        cabinetRepository.save(cabinet);
        cabinetRepository.save(cabinet2);
        cabinetRepository.save(cabinet3);
        cabinetRepository.save(cabinet4);
        cabinetRepository.save(cabinet5);
        cabinetRepository.save(cabinet6);
        fuse7.setCabinetFuse(cabinet);
        fuse7.setAssigned(true);
        fuse8.setCabinetFuse(cabinet2);
        fuse8.setAssigned(true);
        fuse9.setCabinetFuse(cabinet3);
        fuse9.setAssigned(true);
        fuse10.setCabinetFuse(cabinet4);
        fuse10.setAssigned(true);
        fuse10.setFuseExpired(true);
        fuse11.setCabinetFuse(cabinet5);
        fuse11.setAssigned(true);
        fuse11.setFuseExpired(true);
        fuse12.setCabinetFuse(cabinet6);
        fuse12.setAssigned(true);

        HeadWay W3b = new HeadWay(1l, "PKW 1000", 4l, true,  Ess, cabinet );
        HeadWay W3b2 = new HeadWay(2l, "C-2 404/4", 4l, true,  Ess2, cabinet2 );
        HeadWay W3b3 = new HeadWay(3l, "K-8 400/2", 4l, true,  Ess3, cabinet3 );
        HeadWay W3b4 = new HeadWay(4l, "C-12 403/1", 4l, true,  Ess4, cabinet4 );
        HeadWay W3b5 = new HeadWay(5l, "PW-1 400/3", 4l, true,  Ess5, cabinet5 );
        HeadWay W3b6 = new HeadWay(6l, "B-2 403/4", 4l, true,  Ess6, cabinet6 );
        headWayRepository.save(W3b);
        headWayRepository.save(W3b2);
        headWayRepository.save(W3b3);
        headWayRepository.save(W3b4);
        headWayRepository.save(W3b5);
        headWayRepository.save(W3b6);
        Employee employee = new Employee("pierwsza", "pierwsza", "111", 1l);
        Employee employee2 = new Employee("druga", "druga", "222", 2l);
        Employee employee3 = new Employee("trzecia", "trzecia", "333", 3l);
        Employee employee4 = new Employee("czwarta", "czwarta", "444", 4l);
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);

    }
}
