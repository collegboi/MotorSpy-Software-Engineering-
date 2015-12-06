package com.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.DAO.UserDao;
import com.example.business.Car;
import com.example.exceptions.DAOException;

public class UploadCarCommand implements Command {
	
	public UploadCarCommand() {
		
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse) throws DAOException {
		
		UserDao userDao = new UserDao();
		
		String forwardToJsp = "";
		
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String reg = request.getParameter("reg");
		String date = request.getParameter("date");


		if (make != null && model != null && reg != null && date != null){    
			
			Car car = new Car();
			car.setMake(make);
			car.setModel(model);
			car.setCarReg(reg);
			car.setDate(date);

			if (userDao.insertNewVehicle(car)) {

				forwardToJsp = "/uploadSuccess.jsp";				
			}
			else{
				forwardToJsp = "/errorCar.jsp";	
			}
		}
		else {
			forwardToJsp = "/errorCar.jsp";	
		}
		return forwardToJsp;
	}

}
