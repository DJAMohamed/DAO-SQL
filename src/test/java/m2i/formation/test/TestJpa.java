package m2i.formation.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import m2i.formation.model.Adresse;
import m2i.formation.model.Formateur;

public class TestJpa {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("formation-data");		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Adresse ericAdresse = new Adresse("1 rue de la Paix", "", "75008", "Paris", "France"); // L'état de l'objet est : nouveau. N'existe pas en base.
		em.persist(ericAdresse); // Il devient manager : il a un lien avec la base.
		ericAdresse.setCodePostal("75001"); // Managed - Dirty Checking.
		tx.commit(); // Flush : Analyse de tous les changements d'états précédents.
		em.close();
		ericAdresse.setComplement("2ème étage"); // Objet deteach.
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Adresse ericAdresseCopy = em.merge(ericAdresse); // managed : ericAdresseCopy - Deteach : ericAdresse.
		ericAdresseCopy.setCodePostal("");
		em.remove(ericAdresseCopy);
		em.persist(ericAdresseCopy); // Redevient managed, il ne fait pas le delete.
		Formateur eric = em.find(Formateur.class, 1L);
		tx.commit(); // Flush.
		em.close();
		emf.close();
	}
}