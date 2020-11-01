package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.sym.Name;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger l = Logger.getLogger(EmployeServiceImpl.class);
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		l.info("employe added  :"+employe.getId());
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);
		employeRepository.save(employe);

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Departement depManagedEntity = deptRepoistory.findById(depId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		if(depManagedEntity.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employeManagedEntity);
			depManagedEntity.setEmployes(employes);
		}else{

			depManagedEntity.getEmployes().add(employeManagedEntity);

		}

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).get();

		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {

		contratRepoistory.save(contrat);
		l.info("Contrat added with Reference :  :"+contrat.getReference());
		
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);
		contratRepoistory.save(contratManagedEntity);
		l.info("Contrat affected to employe with ID   :"+employeId);
		
	}

	public String getEmployePrenomById(int employeId) {
		l.info("before getting the lastname of the employe");
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		l.info(employeManagedEntity.getPrenom());
		l.info("after getting the lastname of the employe");
		
		return employeManagedEntity.getPrenom();
	}
	public void deleteEmployeById(int employeId)
	{
		try{
			l.info("this is this Id of the employe that will be remved  "+employeId);
			Employe employe = employeRepository.findById(employeId).get();
		
			
			//Desaffecter l'employe de tous les departements
			//c'est le bout master qui permet de mettre a jour
			//la table d'association
			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
			}
			l.info("Before  "+employeId);
			
			employeRepository.delete(employe);
			l.info(" Employe with  ID "+employeId +"is removed");
			
		}catch (Exception e) {
			l.error(e);
		}
	
		
	}

	public void deleteContratById(int contratId) {
		try{
			Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
			l.info("contract with Reference  "+contratId);
			contratRepoistory.delete(contratManagedEntity);
					l.info("contract with Reference  "+contratId+" Deleted");
		}catch (Exception e) {
			// TODO: handle exception
			l.error(e);
		}
		
	}

	public int getNombreEmployeJPQL() {
		l.info("the numbers of employes is :"+employeRepository.countemp());
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		l.info("Names of employes : "+employeRepository.employeNames());
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		List<Employe> employes =employeRepository.getAllEmployeByEntreprisec(entreprise);
		ArrayList <String> Names = new ArrayList<>();
		for (int i = 0; i < employes.size(); i++) {
		     Names.add(employes.get(i).getNom());
		}
			l.info("the name of the company :" + entreprise.getName() +" and  her employes are :" + Names );
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		l.info("delete");
        employeRepository.deleteAllContratJPQL();
        l.info("after deleted");
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		l.info("get All employes "+ (List<Employe>) employeRepository.findAll());
				return (List<Employe>) employeRepository.findAll();
	}

}
