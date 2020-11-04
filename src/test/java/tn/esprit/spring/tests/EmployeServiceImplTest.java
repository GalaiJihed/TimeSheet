package tn.esprit.spring.tests;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EmployeServiceImpl;





@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceImplTest {

  private static final Long USER_ONE_ID = 1L;


  @Mock
  private EmployeRepository repoEmploye;
  @Mock
  private EntrepriseRepository entrepriserepo;
  
  @InjectMocks
  private EmployeServiceImpl EmployeService;

  private Employe employe,employe1,employe2;
  private Entreprise entreprise;
  private Contrat contrat;
  private Departement departement,departement1;
  @Mock
  private ContratRepository repoContrat;
  @Mock
  private DepartementRepository departmentRepo;
  List<Employe> employes; 
  @Before
  public void init() throws ParseException {
	 employes = new ArrayList<>();
	employe = new Employe();
    employe.setId(1);
    employe.setNom("Laouissi");
    employe.setPrenom("Sadok");
    employe.setRole(Role.ADMINISTRATEUR);
    employe.setEmail("LaouissiSadok@gmail.com");
    
    employe1 = new Employe();
    employe1.setId(2);
    employe1.setNom("Galai");
    employe1.setPrenom("Jihed");
    employe1.setRole(Role.INGENIEUR);
    employe1.setEmail("galaijihed94@gmail.com");
    
    employe2 = new Employe();
    employe2.setId(3);
    employe2.setNom("Bouafif");
    employe2.setPrenom("Karim");
    employe2.setRole(Role.INGENIEUR);
    employe2.setEmail("bouafifkarim@gmail.com");
   
    
    SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
    Date date = DateFor.parse("2020/08/08");
    contrat = new Contrat();
    
    contrat.setDateDebut(date);
    contrat.setReference(124);
    contrat.setSalaire(1000);
    contrat.setTypeContrat("CDI");
    contrat.setEmploye(employe);
  
    entreprise =new Entreprise();
    entreprise.setId(1);
    entreprise.setName("Vermeg");
    entreprise.setRaisonSocial("VERMEG FRANCE");
    
    departement = new Departement();
	departement.setId(1);
	departement.setName("Telecom");
	departement1 = new Departement();
	departement1.setId(2);
	departement1.setName("Orange");
	employes.add(employe);
	
	entreprise.addDepartement(departement);
	
	departement1.setEmployes(employes);

  }
  
  
  @Test
  public void createEmploye() {
	  Assert.assertEquals(1, EmployeService.ajouterEmploye(employe));
   
  }
  
  @Test
  public void mettreAjourEmailByEmployeId() {
	// Data preparation
	  Mockito.when(repoEmploye.findById(1)).thenReturn(Optional.of(employe));
	// Method call
	  Assert.assertTrue(EmployeService.mettreAjourEmailByEmployeId("Sadok.laouissi@esprit.tn",1));
   
	}
  
  @Test
  public void affecterEmployeADepartement(){
	  Mockito.doReturn(Optional.of(employe)).when(repoEmploye).findById(1);
	  Mockito.doReturn(Optional.of(departement)).when(departmentRepo).findById(1);
	 Assert.assertTrue(EmployeService.affecterEmployeADepartement(1, 1)); 
  }
  
  @Test
  public void desaffecterEmployeDuDepartement(){
	 // Mockito.doReturn(Optional.of(employe)).when(repoEmploye).findById(1);
	  Mockito.doReturn(Optional.of(departement1)).when(departmentRepo).findById(2);
	 Assert.assertTrue(EmployeService.desaffecterEmployeDuDepartement(1, 2)); 
  }
  
  @Test
  public void ajouterContrat(){
	  Assert.assertEquals(124, EmployeService.ajouterContrat(contrat));
  }
  
  @Test
  public void affecterContratAEmploye(){
	  Mockito.doReturn(Optional.of(employe)).when(repoEmploye).findById(1);
	  Mockito.doReturn(Optional.of(contrat)).when(repoContrat).findById(1);
	 Assert.assertTrue(EmployeService.affecterContratAEmploye(1, 1)); 
  }
  
  
  
  /*************************************Chantouf*************************************/
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
  
  
 
  @Test
  public void deleteContratById() {
    // Data preparation
    
    EmployeService.deleteContratById(contrat.getReference());
    // Verification
  assertEquals(124,contrat.getReference());
  }

  
 
  @Test
  public void getNombreEmployeJPQL() {
	    // Data preparation
	    List<Employe> employes = Arrays.asList(employe, employe1, employe2);
	    Mockito.when(repoEmploye.countemp()).thenReturn(employes.size());

	    // Method call
	    Integer employesQty = EmployeService.getNombreEmployeJPQL();

	    // Verification
	    Assert.assertThat(employesQty, Matchers.is(3));
	  
	  }
  
  @Test
  public void getAllEmployeNamesJPQL() {
    // Data preparation
    List<String> employesNames = Arrays.asList(employe.getNom(),employe1.getNom());
    Mockito.when(repoEmploye.employeNames()).thenReturn(employesNames);

    // Method call
    List<String> employesNamesList = EmployeService.getAllEmployeNamesJPQL();

    // Verification
    Assert.assertThat(employesNamesList, Matchers.hasSize(2));
 
  }
  @Test
  public void getAllEmployeByEntreprise() {
    // Data preparation
    // Method call
    List<Employe> employesByEntreprise = EmployeService.getAllEmployeByEntreprise(entreprise);

    // Verification
    Assert.assertNotNull(employesByEntreprise);
  
  }
  
  @Test
  public void getAllEmployes()
  {
 	 
 	 
 	 
 	 List<Employe> employes = Arrays.asList(employe, employe1, employe2);
 	    Mockito.when(repoEmploye.findAll()).thenReturn(employes);

 	    // Method call
 	    List<Employe> employesQty = EmployeService.getAllEmployes();
 	    		
 	    // Verification
 	    Assert.assertThat(employesQty, Matchers.hasSize(3));
 	 //   Mockito.verifyNoMoreInteractions(repoEmploye);
  
 	 
  }
  /*@Test
  public void  deleteAllContratJPQL()
  {
 	 
 	 List<Contrat> contracts = Arrays.asList(contrat, contrat, contrat);
 	    Mockito.when(repoContrat.findAll()).thenReturn(contracts);

 	    // Method call
 	    EmployeService.deleteAllContratJPQL();

 	    // Verification
 	    Assert.assertThat(contracts, Matchers.is(3));
 	    Mockito.verify(repoEmploye, Mockito.times(1)).deleteAllContratJPQL();
 	    Mockito.verifyNoMoreInteractions(repoEmploye);

 	 
 	 
  }
  */
  
  
  
  
  
  
}