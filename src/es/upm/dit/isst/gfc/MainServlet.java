package es.upm.dit.isst.gfc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.upm.dit.isst.gfc.dao.IvaDAO;
import es.upm.dit.isst.gfc.dao.IvaDAOImpl;
import es.upm.dit.isst.gfc.model.Iva;
import javaQuery.importClass.javaQueryBundle;
import javaQuery.j2ee.GeoLocation;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		IvaDAO dao = IvaDAOImpl.getInstance();
		String IPDetected = String.valueOf(req.getParameter("ip"));
		String countryReceived = String.valueOf(req.getParameter("pais"))
				.toLowerCase().replace(" ", "");
		String domain = String.valueOf(req.getParameter("domain"));

		String getDomain = "";
		PeticionGet get = new PeticionGet();
		getDomain = get.conexionGET(domain);

		/****** Manejo de JSON ******/
		// Respuesta JSON a la URL:
		// http://fact-gest.appspot.com/consultas?domain=http://upm.es
		// Posibilidades de respuesta:
		// {"Permitido" : "no"}
		// {"Permitido" : "si"}

		Boolean respuesta = false;
		try {
			JSONObject obj = new JSONObject(getDomain);
			respuesta = obj.getBoolean("permitido");
		} catch (Exception e) {
		}

		if (respuesta) {
			GeoLocation $gl = javaQueryBundle.createGeoLocation();
			$gl.Country = "undefined";
			$gl.MAPTargetByIP(IPDetected, "Nada");
			String myCountry = $gl.Country.toLowerCase();
			myCountry = myCountry.replace(" ", "");

			req.getSession().setAttribute("IPDetected", IPDetected);
			req.getSession().setAttribute("countryDetected", myCountry);
			req.getSession().setAttribute("countryReceived", countryReceived);
			req.getSession().setAttribute("coincident",
					countryReceived.equals(myCountry));
			req.getSession().setAttribute("domain", domain);
			req.getSession().setAttribute("respuesta", respuesta);

			try {
				if (countryReceived.equals(myCountry)) {
					List<Iva> ivas = new ArrayList<Iva>();
					ivas = dao.getIvas(myCountry);
					req.getSession().setAttribute("iva",
							new ArrayList<Iva>(ivas).get(0).getIva());
					req.getSession().setAttribute("reducedIva",
							new ArrayList<Iva>(ivas).get(0).getReducedIva());
					req.getSession().setAttribute(
							"superReducedIva",
							new ArrayList<Iva>(ivas).get(0)
									.getSuperReducedIva());
				} else {
					req.getSession().setAttribute("iva", "Error!");
					req.getSession().setAttribute("reducedIva", "Error!");
					req.getSession().setAttribute("superReducedIva", "Error!");
				}
			} catch (Exception e) {
			}

			RequestDispatcher view = req
					.getRequestDispatcher("IvaApplication.jsp");
			view.forward(req, resp);

		} else {
			String noEsPosible = "Para comprar créditos acceda a su panel de control contratado";
			req.getSession().setAttribute("noEsPosible", noEsPosible);
			req.getSession().setAttribute("respuesta", respuesta);
			RequestDispatcher view = req
					.getRequestDispatcher("noEsPosible.jsp");
			view.forward(req, resp);
		}

	}

}
