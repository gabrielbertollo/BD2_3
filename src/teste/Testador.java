package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import jpa.JPAUtil;
import jpa.model.Livro;

public class Testador {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		Livro l = new Livro();
		l.setTitulo("Dragoes de Eter");
		l.setAutor("Alien Alflen");
		l.setEditora("Panini");
		l.setAvaliacao(8);
		l.setPreco(19.90);
		
		Livro l2 = new Livro();
		l2.setTitulo("Harry Potter e a Pedra Filosofal");
		l2.setAutor("Jk. Rowling");
		l2.setEditora("HP Rowling");
		l2.setAvaliacao(10);
		l2.setPreco(49.90);
		
		tx.begin();
		em.persist(l);
		
		em.persist(l2);
		//Delete:
		Livro lv = new Livro();
		lv.setId(1l);
		
		em.remove(em.merge(lv));
		
		tx.commit();
		
		
		
		//Consulta com JPQL
		Query query = em.createQuery("Select a from Livro a", Livro.class);
		List<Livro> livro = query.getResultList();
		for (Livro a : livro) {
			System.out.println(a.getTitulo());
		}
		
		
		//Select query nativa:
		String sql = "Select * from Livro where preco < :valorPreco";
		Query sqlQuery = em.createNativeQuery(sql, Livro.class);
		
		sqlQuery.setParameter("valorPreco", 100);
		
		List<Livro> lvros1 = sqlQuery.getResultList();
		
		for (Livro lv1 : lvros1)
			System.out.println(lv1.getTitulo());
		
		
		//Update
		tx.begin();
		Livro novo = em.find(Livro.class, 2l);
		novo.setTitulo("Casos de Familia");
		tx.commit();
		
		
		em.close();
	}

}
