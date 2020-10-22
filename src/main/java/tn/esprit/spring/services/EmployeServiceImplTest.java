package tn.esprit.spring.services;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

public class EmployeServiceImplTest {
	EmployeServiceImpl myClass = new EmployeServiceImpl();
	private static final Logger l = Logger.getLogger(EmployeServiceImplTest.class);
	
	//Sadok
	@Test
	public void testAjouterEmploye() {
		Employe e = new Employe();
		e.setId(19);
		e.setNom("Laouissi");
		e.setPrenom("Sadok");
		e.setActif(true);
		e.setEmail("Sadok.laouissi@esprit.tn");
		e.setRole(Role.ADMINISTRATEUR);
		
		//l.info(e.getNom());
		assertEquals(e.getId(),myClass.ajouterEmploye(e));
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

	//Chantouf
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

	//kimo
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
