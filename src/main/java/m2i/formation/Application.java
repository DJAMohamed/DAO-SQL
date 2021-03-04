package m2i.formation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import m2i.formation.dao.IAdresseDao;
import m2i.formation.dao.IMatiereDao;
import m2i.formation.dao.IPersonneDao;
import m2i.formation.dao.sql.AdresseDaoSql;
import m2i.formation.dao.sql.MatiereDaoSql;
import m2i.formation.dao.sql.PersonneDaoSql;

public class Application {
	private static Application instance = null;

	private final IAdresseDao adresseDao = new AdresseDaoSql();
	private final IMatiereDao matiereDao = new MatiereDaoSql();
	private final IPersonneDao personneDao = new PersonneDaoSql();

	// empêcher d'instancier le singleton depuis l'extérieur
	private Application() {
	}

	// méthode static de création du singleton
	public static Application getInstance() {
		// on ne crée l'instance qu'au premier appel de la méthode
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public IAdresseDao getAdresseDao() {
		return adresseDao;
	}

	public IMatiereDao getMatiereDao() {
		return matiereDao;
	}

	public IPersonneDao getPersonneDao() {
		return personneDao;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/formation-data", "root", "admin");
	}

}
