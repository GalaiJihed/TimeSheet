package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Integer>  {
	
	
}
