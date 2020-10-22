package tn.esprit.spring.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.Console;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.IEmployeService;


@SpringBootTest
public class RestControlEmployeTest {

	@Autowired
	IEmployeService iemployeservice;
	
	
	@MockBean
	private EmployeRepository employeRepository ;
	
	@Before
	public void setUp() {
		
	}
	
	
	@Test
	public void testAjouterEmploye() {
		
		Employe	e = new Employe();
		e.setId(26);
		e.setNom("Laouissi");
		e.setPrenom("Sadok");
		e.setActif(true);
		e.setEmail("Sadok.laouissi@esprit.tn");
		e.setRole(Role.ADMINISTRATEUR);
		
		when(
				employeRepository.save(e)
				).thenReturn(e);
		
		assertSame(e.getId(),iemployeservice.ajouterEmploye(e));
	}

	@Test
	public void testMettreAjourEmailByEmployeId() {
		fail("Not yet implemented");
	}

	@Test
	public void testAffecterEmployeADepartement() {
		fail("Not yet implemented");
	}

	@Test
	public void testDesaffecterEmployeDuDepartement() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterContrat() {
		fail("Not yet implemented");
	}

	@Test
	public void testAffecterContratAEmploye() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmployePrenomById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmployeById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteContratById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombreEmployeJPQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllEmployeNamesJPQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllEmployeByEntreprise() {
		fail("Not yet implemented");
	}

	@Test
	public void testMettreAjourEmailByEmployeIdJPQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllContratJPQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSalaireByEmployeIdJPQL() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSalaireMoyenByDepartementId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTimesheetsByMissionAndDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllEmployes() {
		fail("Not yet implemented");
	}

}
