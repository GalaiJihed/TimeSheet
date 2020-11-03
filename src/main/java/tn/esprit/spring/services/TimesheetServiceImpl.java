package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	private static final Logger l = Logger.getLogger(TimesheetServiceImpl.class);
	
	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	public int ajouterMission(Mission mission) {
		int result = -1;
		try {
			l.info("In ajouterMission : " + mission.toString());
			missionRepository.save(mission);
			result = mission.getId();
		}
		catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out ajouterMission : " + result);
		return mission.getId();
	}
    
	public boolean affecterMissionADepartement(int missionId, int depId) {
		boolean result = false;
		try {
			l.info("In affecterMissionADepartement : depId[" + depId + "] / missionId[" + missionId + "]");
			Mission mission = missionRepository.findById(missionId).get();
			Departement dep = deptRepoistory.findById(depId).get();
			mission.setDepartement(dep);
			missionRepository.save(mission);
			result = true;
		}
		catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out affecterMissionADepartement : " + result + "\n");
		return result;
		
	}

	public boolean ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		
		boolean result = false;
		try {
			l.info("In ajouterTimesheet : missionId[" + missionId + "] / employeId[" + employeId + "] / dateDebut[" + dateDebut + "] / dateFin[" + employeId + "]");
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		result = true;
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out ajouterTimesheet : " + result);
		return result;
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		try {
			l.info("In findAllMissionByEmployeJPQL : employeId[" + employeId + "]");
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		for (Mission mission : timesheetRepository.findAllMissionByEmployeJPQL(employeId) ){
			l.info("Out getAllDepartementsNamesByEntreprise : " + mission + "\n");
		}
		
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		try {
			l.info("In getAllEmployeByMission : missionId[" + missionId + "]");
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		for (Employe employe : timesheetRepository.getAllEmployeByMission(missionId) ){
			l.info("Out getAllEmployeByMission : " + employe + "\n");
		}
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}
