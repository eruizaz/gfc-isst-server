package es.upm.dit.isst.gfc.dao;

import java.util.List;

import es.upm.dit.isst.gfc.model.Iva;

public interface IvaDAO {

	public List<Iva> listIvas();
	public void add (String country, String iva, String reducedIva, String superReducedIva);
	public List<Iva> getIvas(String country);
	public void remove (long id);
	public List<String> getCountries();	
}
