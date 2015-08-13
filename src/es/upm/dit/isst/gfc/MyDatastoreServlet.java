package es.upm.dit.isst.gfc;

import es.upm.dit.isst.gfc.dao.IvaDAO;
import es.upm.dit.isst.gfc.dao.IvaDAOImpl;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDatastoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

@Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	int flag = 1;
	IvaDAO dao = IvaDAOImpl.getInstance();
	if (flag==0){ 
		dao.add("spain", "21", "10", "4");
		dao.add("france", "19,6", "7", "2,1");
		dao.add("austria", "20", "10", "-");
		dao.add("italy", "21", "10", "4");
		dao.add("germany", "19", "7", "-");
		dao.add("belgium", "21", "12", "6");
		dao.add("netherlands", "19", "6", "-");
		dao.add("denmark", "25", "10", "4");
		dao.add("ireland", "23", "13,5", "9");
		dao.add("luxembourg", "15", "12", "3");
		dao.add("greece", "23", "13", "6,5");
		dao.add("portugal", "23", "13", "6");
		dao.add("romania", "24", "9", "5");
		dao.add("bulgaria", "20", "9", "-");
		dao.add("estonia", "20", "8,5", "-");
		dao.add("latvia", "21", "12", "-");
		dao.add("lithuania", "21", "10", "4");
		dao.add("hungary", "27", "18", "5");
		dao.add("croatia", "22", "10", "4");
		dao.add("cyprus", "15", "8", "5");
		dao.add("czechrepublic", "20", "14", "-");
		dao.add("finland", "23", "13", "9");
		dao.add("malta", "18", "7", "5");
		dao.add("poland", "23", "8", "3");
		dao.add("slovakia", "20", "10", "-");
		dao.add("slovenia", "19", "10", "6");
		dao.add("sweden", "25", "12", "6");
		dao.add("unitedkingdom", "20", "5", "-");
		
		flag = 1;
	}
	
  }
}