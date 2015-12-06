package com.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DAO.UserDao;
import com.example.business.Car;
import com.example.exceptions.DAOException;

public class UpdateCarDBCommand implements Command {
	
	public UpdateCarDBCommand() {
		
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse) throws DAOException {
		
		UserDao userDao = new UserDao();
		
		String forwardToJsp = "";
		
		Integer carID = Integer.parseInt(request.getParameter("carID"));
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String reg = request.getParameter("reg");
		String date = request.getParameter("date");


		if (make != null && model != null && reg != null && date != null){    
			
			Car car = new Car();
			car.setCarID(carID);
			car.setMake(make);
			car.setModel(model);
			car.setCarReg(reg);
			car.setDate(date);

			if (userDao.updateVehicle(car)) {

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