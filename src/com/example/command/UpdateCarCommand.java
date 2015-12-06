package com.example.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.business.Car;

public class UpdateCarCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
		
		String forwardToJsp = "";		
		
		
		Integer carID = Integer.parseInt(request.getParameter("carID"));
		List<Car> cars = new ArrayList<>();

		HttpSession session = request.getSession();
		cars = (List)session.getAttribute("car");
		
		Car car = new Car();
		
		for(int i = 0; i < cars.size(); i++) {
			
			if(cars.get(i).getCarID() == carID) {
				car = cars.get(i);
			}
		}
		
		
		session.setAttribute("updateCar", car);
		
		forwardToJsp = "/updateCar.jsp";
	
		return forwardToJsp;
	}

}
