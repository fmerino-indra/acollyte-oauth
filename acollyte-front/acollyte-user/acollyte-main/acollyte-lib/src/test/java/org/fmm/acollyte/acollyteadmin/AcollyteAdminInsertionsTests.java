package org.fmm.acollyte.acollyteadmin;

import org.fmm.acollyte.common.model.EmailAccount;
import org.fmm.acollyte.common.model.MobileNumber;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.ServiceType;
import org.fmm.acollyte.common.model.ServiceTypeEnum;
import org.fmm.acollyte.common.repository.EmailAccountRepository;
import org.fmm.acollyte.common.repository.MobileNumberRepository;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.fmm.acollyte.common.repository.ServiceTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.Assert;

@SpringBootTest(classes = {AcollyteCommonTestApplication.class})
@EntityScan(basePackages = {"org.fmm.acollyte.common"})
@EnableJpaRepositories(basePackages = {"org.fmm.acollyte.common","org.fmm.acollyte.acollyteadmin"})

class AcollyteAdminInsertionsTests {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    
    @Autowired
    MobileNumberRepository mobileNumberRepository;

    @Autowired
    EmailAccountRepository emailAccountRepository;

	@Test
//	@Transactional
	void addPersonsJPQL() {
		addManoloJPQL();
		addJuanviJPQL();
		addPedroJPQL();
		addFelixJPQL();
		addEugenioJPQL();
		addJacoboJPQL();
		addPabloJPQL();
		addLuisJPQL();
		addCarlosJPQL();
		addJoseAurelioJPQL();
	}
	
	private void addManoloJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Manuel Montero");
		aux.setComunidad(1);
		aux.setUserId("manuelmontero777@gmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("609069136");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("manuelmontero777@gmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Juanvi */
	private void addJuanviJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Juanvi");
		aux.setComunidad(1);
		aux.setUserId("juanvi");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("610633926");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
//		ea = new EmailAccount();
//		ea.setEmailAccount("");
//		ea.setPerson(aux);
//		emailAccountRepository.save(ea);
//		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Pedro */ 
	private void addPedroJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Pedro Gutiérrez Gustín");
		aux.setComunidad(2);
		aux.setUserId("pedrotedelval@telefonica.net");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("670867381");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("pedrotedelval@telefonica.net");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	private void addFelixJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;
		
		aux = new Person();
		aux.setName("Félix Merino");
		aux.setComunidad(2);
		aux.setUserId("felix.merino@gmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("660959325");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("felix.merino@gmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Eugenio */
	private void addEugenioJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Eugenio Sanz");
		aux.setComunidad(6);
		aux.setUserId("esanzmonge@gmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("655183112");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("esanzmonge@gmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Jacobo */
	private void addJacoboJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Jacobo García Rodríguez");
		aux.setComunidad(6);
		aux.setUserId("jacgarrod@gmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("687723441");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("jacgarrod@gmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Pablo */
	private void addPabloJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Pablo Guerrero");
		aux.setComunidad(1);
		aux.setUserId("pb.guerrero.vc@gmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("659559311");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("pb.guerrero.vc@gmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Luis */
	private void addLuisJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Luis Soler Carmona");
		aux.setComunidad(7);
		aux.setUserId("luissolercarmona@gmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("638480930");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("luissolercarmona@gmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* Carlos */
	private void addCarlosJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("Carlos Rodríguez");
		aux.setComunidad(7);
		aux.setUserId("carlos10rodriguez@hotmail.com");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("679356309");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
		ea = new EmailAccount();
		ea.setEmailAccount("carlos10rodriguez@hotmail.com");
		ea.setPerson(aux);
		emailAccountRepository.save(ea);
		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	/* José Aurelio */
	private void addJoseAurelioJPQL() {
		Person aux = null;
		MobileNumber mn = null;
		EmailAccount ea = null;

		aux = new Person();
		aux.setName("José Aurelio Prado");
		aux.setComunidad(5);
		aux.setUserId("joseaurelio");
		personRepository.save(aux);
		Assert.notNull(aux.getId(), "El id no puede ser nulo");
		
		mn = new MobileNumber();
		mn.setMobileNumber("657560401");
		mn.setPerson(aux);
		mobileNumberRepository.save(mn);
		Assert.notNull(mn.getId(), "El id no puede ser nulo");
		
//		ea = new EmailAccount();
//		ea.setEmailAccount("");
//		ea.setPerson(aux);
//		emailAccountRepository.save(ea);
//		Assert.notNull(ea.getId(), "El id no puede ser nulo");
	}
	
	
//	@Test
	void addServiceTypesJPQL() {
		ServiceType aux = null;
		aux = ServiceType.from(ServiceTypeEnum.SUNDAY);
		serviceTypeRepository.save(aux);
		aux = ServiceType.from(ServiceTypeEnum.SOLEMNITY);
		serviceTypeRepository.save(aux);
		
		
	}
}