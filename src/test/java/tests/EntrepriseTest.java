package tests;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.controller.RestControlEntreprise;


@RunWith(MockitoJUnitRunner.class)
public class EntrepriseTest {

  private static final Long USER_ONE_ID = 1L;

  @Mock
  private EntrepriseRepository entrepriseRepo;

  @InjectMocks
  private RestControlEntreprise entrepriseController;

  private Entreprise entreprise1, entreprise2, entreprise3;
  
  @Before
  public void init() throws ParseException {
    
	entreprise1 = new Entreprise();
	entreprise1.setId(1);
	entreprise1.setName("SSII Consulting");
	entreprise1.setRaisonSocial("Cite El Ghazela");
    
  }

  @Test
  public void createEntreprise() {
    // Data preparation
    Mockito.when(entrepriseRepo.findById(1)).thenReturn(Optional.of(entreprise1));

    // Method call
    int entrepriseId = entrepriseController.ajouterEntreprise(entreprise1);

    // Verification
    Assert.assertNull(entrepriseId);
    Mockito.verify(entrepriseRepo, Mockito.times(1)).findById(1);
    Mockito.verifyNoMoreInteractions(entrepriseRepo);
  }
}