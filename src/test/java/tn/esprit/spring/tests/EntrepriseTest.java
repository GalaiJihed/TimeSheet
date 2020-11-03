package tn.esprit.spring.tests;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class EntrepriseTest {

  private static final Long USER_ONE_ID = 1L;
  private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

  @Mock
  private EntrepriseRepository entrepriseRepo;
  
  @Mock
  private DepartementRepository departementRepo;

  @InjectMocks
  private EntrepriseServiceImpl entrepriseService;

  private Entreprise entreprise;
  private Departement departement, anotherDepartement;
  
  @Before
  public void init() throws ParseException {
	
	entreprise = new Entreprise();
	entreprise.setId(1);
	entreprise.setName("SSII Consulting");
	entreprise.setRaisonSocial("Cite El Ghazela");
	
	departement = new Departement();
	departement.setId(1);
	departement.setName("Telecom");
	
	anotherDepartement = new Departement();
	anotherDepartement.setId(2);
	anotherDepartement.setName("OoreedoO");
	
	entreprise.addDepartement(departement);
	entreprise.addDepartement(anotherDepartement);
	
  }

  @Test
  public void ajouterEntreprise() {
	 Assert.assertEquals(1, entrepriseService.ajouterEntreprise(entreprise));
  }
  
  @Test
  public void ajouterDepartement() {   
	  Assert.assertEquals(1, entrepriseService.ajouterDepartement(departement)); 
  }
  
  @Test
  public void getEntrepriseById() {
	  Mockito.doReturn(Optional.of(entreprise)).when(entrepriseRepo).findById(1);
	  Entreprise returnedValue = entrepriseService.getEntrepriseById(1);
	  Assert.assertEquals(entreprise, returnedValue); 
  }
  
  @Test
  public void getAllDepartementsNamesByEntreprise() {
	  List<String> shouldReturn = Arrays.asList(departement.getName(), anotherDepartement.getName());
	  Mockito.doReturn(Optional.of(entreprise)).when(entrepriseRepo).findById(1);
	  Assert.assertEquals(entrepriseService.getAllDepartementsNamesByEntreprise(1), shouldReturn); 
	  //Assert.assertThat(entrepriseService.getAllDepartementsNamesByEntreprise(1) , Matchers.hasSize(2));
  }
  
  @Test
  public void affecterDepartementAEntreprise() {
	  Mockito.doReturn(Optional.of(entreprise)).when(entrepriseRepo).findById(1);
	  Mockito.doReturn(Optional.of(departement)).when(departementRepo).findById(1);
	  Assert.assertTrue(entrepriseService.affecterDepartementAEntreprise(1, 1)); 
  }
  
  @Test
  public void deleteEntrepriseById() {
	  Mockito.doReturn(Optional.of(entreprise)).when(entrepriseRepo).findById(1);
	  Assert.assertTrue(entrepriseService.deleteEntrepriseById(1)); 
  }
  
  @Test
  public void deleteDepartementById() {
	  Mockito.doReturn(Optional.of(departement)).when(departementRepo).findById(1);
	  Assert.assertTrue(entrepriseService.deleteDepartementById(1)); 
  }

}