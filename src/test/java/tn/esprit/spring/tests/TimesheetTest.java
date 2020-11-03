package tn.esprit.spring.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.TimesheetServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class TimesheetTest {

  private static final Long USER_ONE_ID = 1L;
  private static final Logger l = Logger.getLogger(TimesheetServiceImpl.class);

  @Mock
  private MissionRepository missionRepo;
  

  @Mock
  private DepartementRepository departmentRepo;
  

  @Mock
  private TimesheetRepository timesheetRepo;
  

  @InjectMocks
  private TimesheetServiceImpl timesheetServiceImpl;

  List<Employe> employes; 
  List<Mission> missions; 
  private Mission mission, mission2;
  private Departement departement;
  private Timesheet timesheet;
  private TimesheetPK timesheetPk;
  private Employe employe;
  
  @Before
  public void init() throws ParseException {
	  missions = new ArrayList<>();
	  employes = new ArrayList<>();
	  
	  
	  employe = new Employe();
	  employe.setId(1);
	  employe.setActif(true);
	  employe.setEmail("bassem");
	  
	  departement = new Departement();
	  departement.setId(1);
	  departement.setName("Telecom");
	  
	  mission = new Mission();
	  mission.setId(1);
	  mission.setName("mission1");
	  mission.setDescription("description");
	  mission.setDepartement(departement);	
	  
	  mission2 = new Mission();
	  mission2.setId(2);
	  mission2.setName("mission2");
	  mission2.setDescription("description2");
	  mission2.setDepartement(departement);	
	  
	  

	  
	  
	  timesheetPk = new TimesheetPK();
	  SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
	  Date dateDebut = DateFor.parse("2020/08/08");
	  timesheetPk.setDateDebut(dateDebut);
	  Date dateFin = DateFor.parse("2020/09/09");
	  timesheetPk.setDateFin(dateFin);
	  timesheetPk.setIdEmploye(1);
	  timesheetPk.setIdMission(1);
	  
	  
	  
	  
	  timesheet = new Timesheet();
	  timesheet.setMission(mission);
	  timesheet.setEmploye(employe);
	  ArrayList<Timesheet> timesheets = new ArrayList<>();
	  timesheets.add(timesheet);
	  

	
	  mission.setTimesheets(timesheets);
	  employe.setTimesheets(timesheets);
	  
	  missions.add(mission);
	  employes.add(employe);

  }

 @Test
  public void ajouterMission() {
	 Assert.assertEquals(1, timesheetServiceImpl.ajouterMission(mission));
  }
 @Test
 public void affecterMissionADepartement() {
	  Mockito.doReturn(Optional.of(mission)).when(missionRepo).findById(1);
	  Mockito.doReturn(Optional.of(departement)).when(departmentRepo).findById(1);
	  Assert.assertTrue(timesheetServiceImpl.affecterMissionADepartement(1, 1));
 }
 @Test
 public void ajouterTimesheet() {
	 Assert.assertTrue(timesheetServiceImpl.ajouterTimesheet(mission.getId(), employe.getId(), timesheetPk.getDateDebut(), timesheetPk.getDateFin()));
 }
 	@Test
	public void findAllMissionByEmployeJPQL() {
	  List<Mission> shouldReturn = Arrays.asList(mission);
	  Mockito.doReturn(missions).when(timesheetRepo).findAllMissionByEmployeJPQL(1);
	  Assert.assertEquals(timesheetServiceImpl.findAllMissionByEmployeJPQL(1), shouldReturn); 
		
	}
 	 @Test
 	 public void getAllEmployeByMission() {
 		List<Employe> shouldReturn = Arrays.asList(employe);
 		  Mockito.doReturn(employes).when(timesheetRepo).getAllEmployeByMission(1);
 		  Assert.assertEquals(timesheetServiceImpl.getAllEmployeByMission(1), shouldReturn); 
 	 }
 

}