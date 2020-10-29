package tn.esprit.spring.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;





@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceImplTest {

  private static final Long USER_ONE_ID = 1L;


  @Mock
  private EmployeRepository repoEmploye;

  @InjectMocks
  private EmployeServiceImpl EmployeService;

  private Employe employe;
  private Contrat contrat;
  @Mock
  private ContratRepository repoContrat;

  
  @Before
  public void init() throws ParseException {
    employe = new Employe();
    employe.setId(1);
    employe.setNom("Carlos");
    employe.setPrenom("Charz");
    employe.setRole(Role.ADMINISTRATEUR);
    employe.setEmail("carlos@yopmail.com");
    SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
    Date date = DateFor.parse("2020/08/08");
    contrat = new Contrat();
    contrat.setDateDebut(date);
    contrat.setReference(124);
    contrat.setSalaire(1000);
    contrat.setTypeContrat("CDI");
    contrat.setEmploye(employe);
  
  }
  @Test
  public void getEmployePrenomById() {
    // Data preparation
    Mockito.when(repoEmploye.findById(1)).thenReturn(Optional.of(employe));

    // Method call
    String employe = EmployeService.getEmployePrenomById(1);

    // Verification
    Assert.assertNotNull(employe);
    Mockito.verify(repoEmploye, Mockito.times(1)).findById(1);
    Mockito.verifyNoMoreInteractions(repoEmploye);
  }
  @Test()
  public void createUserAndUserAlreadyExists() {
    // Data preparation
    Mockito.when(repoEmploye.findById(1)).thenReturn(Optional.of(employe));

    // Method call
    int user = EmployeService.ajouterEmploye(employe);

    // Verification
    Assert.assertNull(user);
    Mockito.verify(repoEmploye, Mockito.times(1)).findById(1);
    Mockito.verifyNoMoreInteractions(repoEmploye);
  }
  
  
  @Test()
  public void AddContrat() {
    // Data preparation
    Mockito.when(repoContrat.findById(1)).thenReturn(Optional.of(contrat));
    // Method call
     int c = EmployeService.ajouterContrat(contrat);
    // Verification
    Assert.assertNull(c);
    Mockito.verify(repoContrat, Mockito.times(1)).findById(contrat.getReference());
    Mockito.verifyNoMoreInteractions(repoEmploye);
  }
  
  @Test
  public void deleteContratById() {
    // Data preparation
    Mockito.when(repoContrat.findById(contrat.getReference())).thenReturn(Optional.of(contrat));
    
    EmployeService.deleteContratById(contrat.getReference());
    // Verification
    Mockito.verify(repoContrat, Mockito.times(1)).deleteById(contrat.getReference());
    Mockito.verifyNoMoreInteractions(repoEmploye);
  }

  @Test
  public void affecterContratAEmploye () {
    // Data preparation
    Mockito.when(repoEmploye.findById(employe.getId())).thenReturn(Optional.of(employe));
    	
    EmployeService.affecterContratAEmploye(contrat.getReference(),employe.getId());
    // Verification
    Mockito.verify(repoContrat, Mockito.times(1)).findById(contrat.getReference());
    Mockito.verify(repoEmploye, Mockito.times(1)).findById(employe.getId());
    Mockito.verifyNoMoreInteractions(repoContrat);
    Mockito.verifyNoMoreInteractions(repoEmploye);
  }
  

}