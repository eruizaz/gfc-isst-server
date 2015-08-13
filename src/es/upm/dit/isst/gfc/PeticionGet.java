package es.upm.dit.isst.gfc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PeticionGet {
	String data;

	public PeticionGet() throws MalformedURLException {

	}

	String conexionGET(String domainSolicitado) {
		String responce = "";
		BufferedReader rd = null;
		
		// https://taxy-gest.appspot.com/consulta?domain=prueba.com
		String request = "https://taxy-gest.appspot.com/consulta?domain=" + domainSolicitado;

		try {
			URL url = new URL(request);
			URLConnection conn2 = url.openConnection();
			rd = new BufferedReader(new InputStreamReader(
						conn2.getInputStream()));
			
			String line;

			while ((line = rd.readLine()) != null) {
				responce += line;
			}
		} catch (Exception e) {
			System.out.println("Web request failed");
			// Web request failed
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException ex) {
					System.out.println("Problema al cerrar el objeto lector");
				}
			}
		}
		return responce;
	}
	
	String conexionGETm(String domain, String iva, String tipoIva, String pais, String nFactura) {
		String responce = "";
		BufferedReader rd = null;
		
		/* 
		 * Ejemplo dominio consultado:
		 * https://taxy-gest.appspot.com/factura
		 * ?domain=prueba.com&iva=1234&tipoIva=reducedIva&pais=spain&nFactura=1234
		 * 
		 * Posibles "tipoIva":
		 * 			iva
		 * 			reducedIva
		 * 			superReducedIva
		 * 
		 * Posibles "pais":
		 * 			spain
		 * 			unitedkingdom
		 * 			france
		 * 			czechrepublic
		 * 			...
		*/
		// http://eruiz-test.appspot.com/		
		String request = "https://taxy-gest.appspot.com/factura?domain=" 
				+ domain + "&iva=" + iva + "&tipoIva=" + tipoIva
				+ "&pais=" + pais + "&nFactura=" + nFactura;

		try {
			URL url = new URL(request);
			URLConnection conn2 = url.openConnection();
			rd = new BufferedReader(new InputStreamReader(
						conn2.getInputStream()));
			
			String line;

			while ((line = rd.readLine()) != null) {
				responce += line;
			}
		} catch (Exception e) {
			System.out.println("Web request failed");
			// Web request failed
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException ex) {
					System.out.println("Problema al cerrar el objeto lector");
				}
			}
		}
		System.out.println(responce);
		return responce;
	}
	
	String conexionGETmas(String domain, String ivaCalculado, String tipoIva, String pais, String nFactura) {
		String respuesta = "";
		BufferedReader rd = null;

		/* 
		 * Ejemplo dominio consultado:
		 * https://fact-gest.appspot.com/consulta
		 * ?domain=prueba.com&iva=1234&tipoIva=reducedIva&pais=spain&nFactura=1234
		 * 
		 * Posibles "tipoIva":
		 * 			iva
		 * 			reducedIva
		 * 			superReducedIva
		 * 
		 * Posibles "pais":
		 * 			spain
		 * 			unitedkingdom
		 * 			france
		 * 			czechrepublic
		 * 			...
		*/
		// http://eruiz-test.appspot.com/
		String request = "http://eruiz-test.appspot.com/?domain="
				+ domain + "&iva=" + ivaCalculado + "&tipoIva=" + tipoIva
				+ "&pais=" + pais + "&nFactura=" + nFactura;

		try {
			URL url = new URL(request);
			URLConnection conn2 = url.openConnection();
			rd = new BufferedReader(new InputStreamReader(
					conn2.getInputStream()));

			String line;

			while ((line = rd.readLine()) != null) {
				respuesta += line;
			}
		} catch (Exception e) {
			System.out.println("Web request failed");
			// Web request failed
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (IOException ex) {
					System.out.println("Problema al cerrar el objeto lector");
				}
			}
		}
		return respuesta;
	}
}