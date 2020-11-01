package tests;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class EntrepriseTest {

  private static final Long USER_ONE_ID = 1L;
  private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

  @Mock
  private EntrepriseRepository entrepriseRepo;

  @InjectMocks
  private EntrepriseServiceImpl entrepriseService;

  private Entreprise entreprise;
  private Departement departement;
  
  @Before
  public void init() throws ParseException {
	  
	entreprise = new Entreprise();
	entreprise.setId(1);
	entreprise.setName("SSII Consulting");
	entreprise.setRaisonSocial("Cite El Ghazela");
	
	departement = new Departement();
	departement.setId(1);
	departement.setName("Telecom");
  }

  @Test
  public void createEntreprise() {   
    Assert.assertEquals(1, entrepriseService.ajouterEntreprise(entreprise));
  }
  
  @Test
  public void ajouterDepartement() {   
	Assert.assertEquals(1, entrepriseService.ajouterDepartement(departement)); 
  }
  
  @Test
  public void affecterDepartementAEntreprise() { 
	Assert.assertTrue(entrepriseService.affecterDepartementAEntreprise(departement.getId(), entreprise.getId())); 
  }
  
  @Test
  public void getAllDepartementsNamesByEntreprise() { 
	Assert.assertEquals(0, entrepriseService.getAllDepartementsNamesByEntreprise(entreprise.getId()).size()); 
  }
  
  
  
}