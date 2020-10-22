package tn.esprit.spring.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import tn.esprit.spring.entities.Employe;

public class RestControlEmployeTest {
	
	RestControlEmploye myClass = new RestControlEmploye();
	
	// Sadok

	@Test
	public void testajouterEmploye() {
		Employe e = new Employe();
		e.setNom("Sadok");
		assertNotNull(myClass.ajouterEmploye(e));
	}
	@Test
	public void testmettreAjourEmailByEmployeId() {
		fail("Not yet implemented");
	}
	@Test
	public void testaffecterEmployeADepartement() {
		fail("Not yet implemented");
	}
	@Test
	public void testdesaffecterEmployeDuDepartement() {
		fail("Not yet implemented");
	}
	@Test
	public void testajouterContrat() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testaffecterContratAEmploye() {
		fail("Not yet implemented");
	}
	
	// end Sadok
	
		// Chantouf
	@Test
	public void testgetEmployePrenomById() {
		fail("Not yet implemented");
	}
	@Test
	public void testdeleteEmployeById() {
		fail("Not yet implemented");
	}
	@Test
	public void testdeleteContratById() {
		fail("Not yet implemented");
	}
	@Test
	public void testgetNombreEmployeJPQL() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testgetAllEmployeNamesJPQL() {
		fail("Not yet implemented");
	}
	@Test
	public void testgetAllEmployeByEntreprise() {
		fail("Not yet implemented");
	}
	
	// end Chantouf
	
	// Kimo
	@Test
	public void testmettreAjourEmailByEmployeIdJPQL() {
		fail("Not yet implemented");
	}
	@Test
	public void testdeleteAllContratJPQL() {
		fail("Not yet implemented");
	}
	@Test
	public void testgetSalaireByEmployeIdJPQL() {
		fail("Not yet implemented");
	}
	@Test
	public void testgetSalaireMoyenByDepartementId() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetTimesheetsByMissionAndDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testgetAllEmployes() {
		fail("Not yet implemented");
	}
}
