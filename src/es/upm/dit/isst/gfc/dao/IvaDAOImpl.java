package es.upm.dit.isst.gfc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.gfc.model.Iva;

public class IvaDAOImpl implements IvaDAO {

	private static IvaDAOImpl instance;

	private IvaDAOImpl() {
	}

	public static IvaDAOImpl getInstance(){
		if (instance == null)
			instance = new IvaDAOImpl();
		return instance;
	}


	@Override
	public List<Iva> listIvas() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Iva m");
		List<Iva> ivas = q.getResultList();
		return ivas;
	}

	@Override
	public void add(String country, String iva, String reducedIva,
			String superReducedIva) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Iva iva2 = new Iva(country, iva, reducedIva, superReducedIva);
			em.persist(iva2);
			em.close();
		}

	}

	@Override
	public List<Iva> getIvas(String country) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Iva t where t.country = :country");
		q.setParameter("country", country);
		List<Iva> ivas = q.getResultList();
		return ivas;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Iva iva = em.find(Iva.class, id);
			em.remove(iva);
		} finally {
			em.close();
		}
	}

	@Override
	public List<String> getCountries() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select distinct t.country from Iva t");
		List<String> countries = q.getResultList();
		return countries;
	}
}
