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

public class SendFactureDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String domain = String.valueOf(req.getParameter("domain"));
		String ivaCalculado = String.valueOf(req.getParameter("iva"));
		String tipoIva = String.valueOf(req.getParameter("tipoIva"));
		String pais = String.valueOf(req.getParameter("pais"));
		String nFactura = String.valueOf(req.getParameter("nFactura"));
		

		String respuestaConexionGET = "";
		PeticionGet get = new PeticionGet();
		respuestaConexionGET = get.conexionGETm(domain, ivaCalculado, tipoIva, pais, nFactura);

		/****** Manejo de JSON ******/
		// Respuesta JSON a la URL:
		// http://fact-gest.appspot.com/consultas?domain=http://upm.es
		// Posibilidades de respuesta:
		// {"Permitido" : "no"}
		// {"Permitido" : "si"}

		Boolean respuesta = false;
		String errores = "";
		try {
			JSONObject obj = new JSONObject(respuestaConexionGET);
			respuesta = obj.getBoolean("permitido");
			errores = obj.getString("errores");
		} catch (Exception e) {
		}
		System.out.println(respuesta);
		req.getSession().setAttribute("permitido", respuesta);
		if (respuesta) {
			req.getSession().setAttribute("errores", "");
		} else {
			req.getSession().setAttribute("errores", errores);
		}
		RequestDispatcher view = req
				.getRequestDispatcher("enviadoAGestor.jsp");
		view.forward(req, resp);
	}

}
