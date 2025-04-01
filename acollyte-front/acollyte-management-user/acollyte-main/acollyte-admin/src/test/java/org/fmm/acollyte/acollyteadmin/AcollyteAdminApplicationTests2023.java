package org.fmm.acollyte.acollyteadmin;

import static org.fmm.acollyte.common.model.SolemnityEnum.ASUNCION;
import static org.fmm.acollyte.common.model.SolemnityEnum.DOMINGO_RESURRECCION;
import static org.fmm.acollyte.common.model.SolemnityEnum.EPIFANIA;
import static org.fmm.acollyte.common.model.SolemnityEnum.INMACULADA;
import static org.fmm.acollyte.common.model.SolemnityEnum.JUEVES_SANTO;
import static org.fmm.acollyte.common.model.SolemnityEnum.NATIVIDAD;
import static org.fmm.acollyte.common.model.SolemnityEnum.RAMOS;
import static org.fmm.acollyte.common.model.SolemnityEnum.SANTA_MARIA;
import static org.fmm.acollyte.common.model.SolemnityEnum.SANTIAGO;
import static org.fmm.acollyte.common.model.SolemnityEnum.SANTOS;
import static org.fmm.acollyte.common.model.SolemnityEnum.SAN_JOSE;
import static org.fmm.acollyte.common.model.SolemnityEnum.VIERNES_SANTO;
import static org.fmm.acollyte.common.model.SolemnityEnum.VIGILIA_PASCUAL;

import java.time.LocalDate;
import java.time.LocalTime;
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
import org.fmm.acollyte.common.model.Service;
import org.fmm.acollyte.common.model.ServiceType;
import org.fmm.acollyte.common.model.ServiceTypeEnum;
import org.fmm.acollyte.common.model.Solemnity;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.fmm.acollyte.common.repository.SolemnityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

@SpringBootTest(classes = {AcollyteAdminApplication.class})
@AutoConfigureMockMvc
class AcollyteAdminApplicationTests2023 {

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
    
    @Autowired
    SolemnityRepository solemnityRepository;

    @Test
    void testRaffle() {
        RaffleDTO raffle = null;

//        raffle = acollyteAdminService.prepareRaffle(LocalDate.of(2023,1,1), LocalDate.of(2023,3,31));
//        raffle = acollyteAdminService.raffle(raffle);
//        Assert.notNull(raffle, "Raffle OK");

        raffle = acollyteAdminService.prepareRaffle(LocalDate.of(2023,4,1), LocalDate.of(2023,6,30));
        raffle = acollyteAdminService.raffle(raffle);
        Assert.notNull(raffle, "Raffle OK");
    }

//    @Test
    void testListService() {
        LocalTime time = LocalTime.of(23, 59, 59);

        LocalDate fromLDT, toLDT;
        fromLDT = LocalDate.of(2023,01,01);
        toLDT = LocalDate.of(2023,12,31);
        
        ZonedDateTime from, to;
        from = DateUtil.localDateToZonedDateTime(fromLDT);
        to = DateUtil.localDateToZonedDateTime(toLDT, time);
        
        List<Service> servicios = null;
        servicios = serviceRepository.listServicesFromTo(from, to); 
        Assert.notNull(servicios, "Services listed ok, something went bad");
    }
//	  @Test
//	  @Transactional
    void testCreateServices() {
	    List<Service> services = null;
        services = acollyteAdminService.createSundays(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), LocalTime.of(12, 0));
        Assert.notNull(services, "Services created OK");
    }

//    @Test
    void test2023CreateSolemnities() {
    	// Definites
    	/*
    	 * 01/01/2023 -> Santa María Madre de Dios
    	 * 06/01/2023 -> Epifanía del Señor
    	 * 19/03/2023 -> San José Esposo de la Santísima Virgen María
    	 * 25/07/2023 -> Santiago, apóstol (martes)
    	 * 15/08/2023 -> Asunción de la bienaventurada Virgen María (martes)
    	 * 01/11/2023 -> Todos los Santos (miércoles)
    	 * 08/12/2023 -> Inmaculada Concepción de la bienaventurada Virgen María (viernes)
    	 * 25/12/2023 -> Natividad del Señor (lunes)
    	 */
    	
    	// Movables
    	/*
    	 * 08/01/2023 -> Bautismo del Señor (siempre cae en domingo)
    	 * 22/02/2023 -> Miércoles de Ceniza (siempre cae en miércoles)
    	 * 02/04/2023 -> Domingo de Ramos
    	 * 06/04/2023 -> Jueves Santo
    	 * 07/04/2023 -> Viernes Santo
    	 * 08/04/2023 -> Vigilia Pascual
    	 * 09/04/2023 -> Domingo de Resurrección (siempre cae en domingo)
    	 * 21/05/2023 -> Ascensión del Señor (siempre cae en domingo)
    	 * 28/05/2023 -> Domingo de Pentecostés (siempre cae en domingo)
    	 * 01/06/2023 -> Jesucristo, sumo y eterno Sacerdote (no es precepto)
    	 * 04/06/2023 -> Santísima Trinidad (siempre cae en domingo)
    	 * 11/06/2023 -> Santísimo Cuerpo y Sangre de Cristo (siempre cae en domingo)
    	 * Santa Catalina de Siena
    	 * 
    	 */
    	
    	int year = 2023;
    	Solemnity solemnity = null;
    	
    	solemnity = new Solemnity();
    	solemnity.setId(SANTA_MARIA.getId());
    	solemnity.setDate(LocalDate.of(year, 1, 1));
    	solemnity.setName(SANTA_MARIA.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 1);
    	solemnity.setMonth((short) 1);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(EPIFANIA.getId());
    	solemnity.setDate(LocalDate.of(year, 1, 6));
    	solemnity.setName(EPIFANIA.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 6);
    	solemnity.setMonth((short) 1);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(SAN_JOSE.getId());
    	solemnity.setDate(LocalDate.of(year, 3, 19));
    	solemnity.setName(SAN_JOSE.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 19);
    	solemnity.setMonth((short) 3);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(SANTIAGO.getId());
    	solemnity.setDate(LocalDate.of(year, 7, 25));
    	solemnity.setName(SANTIAGO.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 25);
    	solemnity.setMonth((short) 7);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(ASUNCION.getId());
    	solemnity.setDate(LocalDate.of(year, 8, 15));
    	solemnity.setName(ASUNCION.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 15);
    	solemnity.setMonth((short) 8);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(SANTOS.getId());
    	solemnity.setDate(LocalDate.of(year, 11, 1));
    	solemnity.setName(SANTOS.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 1);
    	solemnity.setMonth((short) 11);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(INMACULADA.getId());
    	solemnity.setDate(LocalDate.of(year, 12, 8));
    	solemnity.setName(INMACULADA.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 8);
    	solemnity.setMonth((short) 12);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(NATIVIDAD.getId());
    	solemnity.setDate(LocalDate.of(year, 12, 25));
    	solemnity.setName(NATIVIDAD.getName());
    	solemnity.setDefinite(true);
    	solemnity.setDay((short) 25);
    	solemnity.setMonth((short) 12);
    	solemnityRepository.save(solemnity);
    	
    	// Movables
    	
    	solemnity = new Solemnity();
    	solemnity.setId(RAMOS.getId());
    	solemnity.setDate(LocalDate.of(year, 4, 2));
    	solemnity.setName(RAMOS.getName());
    	solemnity.setDefinite(false);
    	solemnity.setDay((short) 2);
    	solemnity.setMonth((short) 4);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(JUEVES_SANTO.getId());
    	solemnity.setDate(LocalDate.of(year, 4, 6));
    	solemnity.setName(JUEVES_SANTO.getName());
    	solemnity.setDefinite(false);
    	solemnity.setDay((short) 6);
    	solemnity.setMonth((short) 4);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(VIERNES_SANTO.getId());
    	solemnity.setDate(LocalDate.of(year, 4, 7));
    	solemnity.setName(VIERNES_SANTO.getName());
    	solemnity.setDefinite(false);
    	solemnity.setDay((short) 7);
    	solemnity.setMonth((short) 4);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(VIGILIA_PASCUAL.getId());
    	solemnity.setDate(LocalDate.of(year, 4, 8));
    	solemnity.setName(VIGILIA_PASCUAL.getName());
    	solemnity.setDefinite(false);
    	solemnity.setDay((short) 8);
    	solemnity.setMonth((short) 4);
    	solemnityRepository.save(solemnity);
    	
    	solemnity = new Solemnity();
    	solemnity.setId(DOMINGO_RESURRECCION.getId());
    	solemnity.setDate(LocalDate.of(year, 4, 9));
    	solemnity.setName(DOMINGO_RESURRECCION.getName());
    	solemnity.setDefinite(false);
    	solemnity.setDay((short) 9);
    	solemnity.setMonth((short) 4);
    	solemnityRepository.save(solemnity);
    }
    
//    @Test
    void test2023SolemnityServices() {
    	List<Solemnity> solemnities = null;
    	solemnities = solemnityRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    	solemnities = solemnityRepository.findByOrderByDateAsc();
    	Assert.notNull(solemnities, "Algo fue mal");
    	
    	Solemnity solemnity = null;
    	Service service = null;
    	ServiceType st = ServiceType.from(ServiceTypeEnum.SOLEMNITY);
    	// Santa María
    	solemnity = solemnityRepository.findById(SANTA_MARIA.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Epifanía
    	solemnity = solemnityRepository.findById(EPIFANIA.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// San José
    	solemnity = solemnityRepository.findById(SAN_JOSE.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Santiago
    	solemnity = solemnityRepository.findById(SANTIAGO.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Asunción
    	solemnity = solemnityRepository.findById(ASUNCION.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Santos
    	solemnity = solemnityRepository.findById(SANTOS.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Inmaculada
    	// Víspera
    	solemnity = solemnityRepository.findById(INMACULADA.getId()).get();
    	service = new Service();
    	service.setServiceName("Víspera de " + solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate().minusDays(1l), LocalTime.of(21, 00)));
    	service.setNeeded(4);
    	service.setnReserves(0);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Día de fiesta
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(2);
    	service.setnReserves(2);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Natividad
    	solemnity = solemnityRepository.findById(NATIVIDAD.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(true);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(1);
    	service.setnReserves(1);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Ramos
    	solemnity = solemnityRepository.findById(RAMOS.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(8, 30)));
    	service.setNeeded(6);
    	service.setnReserves(0);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(2);
    	service.setnReserves(2);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Jueves Santo
    	solemnity = solemnityRepository.findById(JUEVES_SANTO.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(17, 00)));
    	service.setNeeded(6);
    	service.setnReserves(0);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Viernes Santo
    	solemnity = solemnityRepository.findById(VIERNES_SANTO.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(17, 00)));
    	service.setNeeded(3);
    	service.setnReserves(0);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Vigilia Pascual
    	solemnity = solemnityRepository.findById(VIGILIA_PASCUAL.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(21, 00)));
    	service.setNeeded(6);
    	service.setnReserves(0);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	// Domingo de Resurrección
    	solemnity = solemnityRepository.findById(DOMINGO_RESURRECCION.getId()).get();
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(10, 30)));
    	service.setNeeded(2);
    	service.setnReserves(2);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
    	service = new Service();
    	service.setServiceName(solemnity.getName());
    	service.setEve(false);
    	service.setFixed(false);

    	service.setServiceDate(DateUtil.localDateToZonedDateTime(solemnity.getDate(), LocalTime.of(12, 00)));
    	service.setNeeded(2);
    	service.setnReserves(2);
    	service.setServiceTypeBean(st);
    	serviceRepository.save(service);
    	
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
