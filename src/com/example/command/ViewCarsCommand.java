package com.example.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.DAO.PublicDAO;
import com.example.business.Car;
import com.example.exceptions.DAOException;

public class ViewCarsCommand implements Command {
	
	public ViewCarsCommand() {
		
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse) throws DAOException {
		
		List<Car> allCars = new ArrayList<Car>();
		
		String forwardToJsp = "";
		
		String viewType = (String)request.getParameter("boolean");
		System.out.println(viewType);
		
		PublicDAO publicDao = new PublicDAO();
		
		allCars = publicDao.getAllVehicles();
		
		if(!allCars.isEmpty()) {
				
				HttpSession session = request.getSession();
				
				session.setAttribute("car", allCars);
				session.setAttribute("view", "0");
				

				forwardToJsp = "/allCars.jsp";				
		}
		else {
			forwardToJsp = "/loginFailure.jsp";	
		}
		return forwardToJsp;
	}
}
