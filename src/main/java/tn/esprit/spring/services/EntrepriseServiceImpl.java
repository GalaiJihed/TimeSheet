package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	@Autowired
    DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		int result = -1;
		try {
			l.info("In ajouterEntreprise : " + entreprise.toString());
			entrepriseRepoistory.save(entreprise);
			result = entreprise.getId();
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out ajouterEntreprise : " + result + "\n");
		return result;
	}

	public int ajouterDepartement(Departement dep) {
		int result = -1;
		try {
			l.info("In ajouterDepartement : " + dep.toString());
			deptRepoistory.save(dep);
			result = dep.getId();
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out ajouterDepartement : " + result + "\n");
		return result;
	}
	
	public boolean affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
		//donc il faut rajouter l'entreprise a departement 
		// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
		//Rappel : la classe qui contient mappedBy represente le bout Slave
		//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		boolean result = false;
		try {
			l.info("In affecterDepartementAEntreprise : depId[" + depId + "] / entrepriseId[" + entrepriseId + "]");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			Departement depManagedEntity = deptRepoistory.findById(depId).get();	
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);
			result = true;
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out affecterDepartementAEntreprise : " + result + "\n");
		return result;
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		List<String> depNames = new ArrayList<>();
		try {
			l.info("In getAllDepartementsNamesByEntreprise : entrepriseId[" + entrepriseId + "]");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out getAllDepartementsNamesByEntreprise : " + depNames + "\n");
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Entreprise entreprise = new Entreprise();
		try {
			l.info("In getEntrepriseById : entrepriseId[" + entrepriseId + "]");
			entreprise = entrepriseRepoistory.findById(entrepriseId).get();
		} catch (Exception e) {
			l.error("Erreur : " + e);
		}
		l.info("Out getEntrepriseById : " + entreprise + "\n");
		
		return entreprise; 	
	}

}
