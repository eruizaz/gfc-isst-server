package es.upm.dit.isst.gfc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Iva implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String country;

	private String iva;
	private String reducedIva;
	private String superReducedIva;

	public Iva(String country, String iva, String reducedIva,
			String superReducedIva) {
		this.country = country;
		this.iva = iva;
		this.reducedIva = reducedIva;
		this.superReducedIva = superReducedIva;
	}

	public Long getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String shortDescription) {
		this.iva = shortDescription;
	}

	public String getReducedIva() {
		return reducedIva;
	}

	public void setReducedIva(String longDescription) {
		this.reducedIva = longDescription;
	}

	public String getSuperReducedIva() {
		return superReducedIva;
	}

	public void setSuperReducedIva(String url) {
		this.superReducedIva = url;
	}

} 
