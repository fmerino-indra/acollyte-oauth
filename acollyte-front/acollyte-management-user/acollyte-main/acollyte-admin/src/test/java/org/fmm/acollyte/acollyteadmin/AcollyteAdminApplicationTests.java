package org.fmm.acollyte.acollyteadmin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.fmm.acollyte.acollyteadmin.repository.AbsenceRepository;
import org.fmm.acollyte.acollyteadmin.repository.RaffleRepository;
import org.fmm.acollyte.acollyteadmin.repository.ReserveRafflePersonRepository;
import org.fmm.acollyte.acollyteadmin.repository.ServiceRepository;
import org.fmm.acollyte.acollyteadmin.service.AbsenceMgmtService;
import org.fmm.acollyte.acollyteadmin.service.AcollyteAdminService;
import org.fmm.acollyte.acollyteadmin.service.DateUtil;
import org.fmm.acollyte.acollyteadmin.service.RaffleDTO;
import org.fmm.acollyte.common.model.Absence;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.Raffle;
import org.fmm.acollyte.common.model.RafflePerson;
import org.fmm.acollyte.common.model.Service;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {AcollyteAdminApplication.class})
@AutoConfigureMockMvc
class AcollyteAdminApplicationTests {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    ReserveRafflePersonRepository servicePersonRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    AcollyteAdminService acollyteAdminService;

    @Autowired
    AbsenceMgmtService absenceService;

    @Autowired
    RaffleRepository raffleRepository;

//    @Test
    void testJPQL1() {
        List<Service> services = null;
        services = serviceRepository.listOrderedServices();
        Assert.notNull(services, "El servicio existe, algo fue mal");
    }
//	@Test
    void testJPQL() {
        List<RafflePerson> servicePersons;
        servicePersons = servicePersonRepository.listServicePersons();
        System.out.println(servicePersons);

        RafflePerson sp = null;
        Person person = new Person();
        person.setId(1);
        Service service = new Service();
        service.setId(25);
        sp = servicePersonRepository.findServicesByPersonAndService(person, service);
        Assert.notNull(sp, "El servicio existe, algo fue mal");
    }

//    @Test
    void testJPQL2() {
        LocalDate date = null;
        date = LocalDate.of(2020, 7, 5);
        List<Absence> absences = null;

        absences = absenceRepository.findAbsenceByDate(date);
        Assert.notNull(absences, "Debe devolver una lista vacía");
        Assert.isTrue(absences.size() > 0, "Ha encontrado datos");
    }

@SuppressWarnings("unused")
//    @Test
    void testJPQL3() {
        LocalDate fromLD = null;
        LocalDate toLD = null;
        
        LocalDateTime fromLDT = null;
        LocalDateTime toLDT = null;

        OffsetDateTime from = null;
        OffsetDateTime to = null;
        
        LocalTime time00 = null;
        time00 = LocalTime.of(0, 0);
        
        LocalTime time23 = null;
        time23 = LocalTime.of(23, 59, 59);
        
        fromLD = LocalDate.of(2020, 7, 1);
        toLD = LocalDate.of(2020, 9, 30);

        fromLDT = fromLD.atStartOfDay();
        toLDT = toLD.atTime(time23);
        
        from = OffsetDateTime.of(fromLD, time00, OffsetDateTime.now().getOffset());
        to = OffsetDateTime.of(toLD, time23, OffsetDateTime.now().getOffset());
        
        List<Raffle> raffleList = null;
        raffleList = raffleRepository.selectByDates(from,to);
        Assert.notNull(raffleList, "Debe devolver una lista vacía");
        Assert.isTrue(raffleList.size() > 0, "Ha encontrado datos");

    }

//@Test
    void testRaffle() {
        RaffleDTO raffle = null;

        raffle = acollyteAdminService.prepareRaffle(LocalDate.of(2021,7,01), LocalDate.of(2021,9,30));
        raffle = acollyteAdminService.raffle(raffle);
        Assert.notNull(raffle, "Raffle OK");
    }

//    @Test
    @SuppressWarnings("unused")
    void testListService() {
        LocalTime time = LocalTime.of(23, 59, 59);

        LocalDate fromLDT, toLDT;
        fromLDT = LocalDate.of(2021,01,01);
        toLDT = LocalDate.of(2021,12,31);
        
        ZonedDateTime from, to;
        from = DateUtil.localDateToZonedDateTime(fromLDT);
        to = DateUtil.localDateToZonedDateTime(toLDT, time);
        
        List<Service> servicios = null;
        servicios = serviceRepository.listServicesFromTo(from, to); 
    }
	@Test
//	@Transactional
    void testCreateServices() {
	    List<Service> services = null;
        services = acollyteAdminService.createSundays(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), LocalTime.of(12, 0));
        Assert.notNull(services, "Services created OK");
    }

//@Test
    // Verano 2021 - 3º Trimestre
    void test202103Vacaciones() {
        test202103Manolo();
        test202103Juanvi();
        test202103Pedro();
        test202103Rafa();
        test202103Felix();
        test202103Eugenio();
        test202103Jacobo();
        test202103Pablo();
        test202103MiguelFernández();
        test202103Luis();
        test202103Carlos();
        test202103MiguelGalera();
        test202103JoséAurelio();
        test202103Jesus();
    }

    private void procesarVacaciones(Integer idPerson, LocalDate from, LocalDate to) {
        absenceService.processHolidays(idPerson, from, to);
    }
    private void procesarVacaciones(Integer idPerson, List<LocalDate> holidays) {
        absenceService.createAbsencesByPerson(idPerson, holidays);
    }
    
    private void test202103Manolo() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 8, 1);
        to = LocalDate.of(2021, 8, 15);
        procesarVacaciones(1, from, to);
    }
    private void test202103Juanvi() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 10);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(2, from, to);
    }
    private void test202103Pedro() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 7, 31);
        procesarVacaciones(3, from, to);
    }
    private void test202103Rafa() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 8, 1);
        to = LocalDate.of(2021, 8, 15);
        procesarVacaciones(4, from, to);
    }
    private void test202103Felix() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 15);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(5, from, to);
    }
    private void test202103Eugenio() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(6, from, to);
    }
    private void test202103Jacobo() {
        LocalDate from = null;
        LocalDate to = null;

        List<LocalDate> holidays = null;
        
        holidays = new ArrayList<LocalDate>();

        from = LocalDate.of(2021, 7, 4);
        holidays.add(from);
        
        from = LocalDate.of(2021, 7, 25);
        holidays.add(from);
        
        from = LocalDate.of(2021, 8, 1);
        to = LocalDate.of(2021, 8, 15);
        
        holidays.addAll(DateUtil.sundaysBetween(from, to));
        from = LocalDate.of(2021, 9, 5);
        
        procesarVacaciones(7, holidays);
    }
    private void test202103Pablo() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(8, from, to);
    }
    private void test202103MiguelFernández() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 7, 15);
        procesarVacaciones(9, from, to);

        from = LocalDate.of(2021, 8, 15);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(9, from, to);
    }
    private void test202103Luis() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 8, 15);
        procesarVacaciones(10, from, to);
    }
    private void test202103Carlos() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 8, 1);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(11, from, to);

    }
    private void test202103MiguelGalera() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 15);
        to = LocalDate.of(2021, 8, 31);
        procesarVacaciones(12, from, to);
    }
    private void test202103JoséAurelio() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 9, 30);
        procesarVacaciones(13, from, to);
    }
    private void test202103Jesus() {
        LocalDate from = null;
        LocalDate to = null;

        from = LocalDate.of(2021, 7, 1);
        to = LocalDate.of(2021, 9, 30);
        procesarVacaciones(14, from, to);
    }

}
