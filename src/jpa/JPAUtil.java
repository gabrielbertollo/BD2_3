package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
