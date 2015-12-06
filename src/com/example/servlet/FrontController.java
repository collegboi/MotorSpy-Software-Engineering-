package com.example.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.command.Command;
import com.example.command.CommandFactory;
import com.example.exceptions.CommandException;
import com.example.exceptions.DAOException;

@WebServlet(urlPatterns={"/FrontController"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_ACTION = "LoginUser";
       

    public FrontController() {
        super();
    
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest (request, response);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
	}	
	
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws DAOException {

		String forwardToJsp = null;		
		String action = request.getParameter("action");
		
		
		if ( !action.equalsIgnoreCase(LOGIN_ACTION) ){

			HttpSession session = request.getSession();

			//we do not need to be logged in for any commands to work
			if ( session.getId() != session.getAttribute("loggedSessionId") ){
				
			}
			
		} 
		
		CommandFactory factory = CommandFactory.getInstance();
		Command command = null;
		
		try {
			command = factory.createCommand(action);
			forwardToJsp = command.execute(request, response);
		} catch (CommandException e) {			
			e.printStackTrace();
			forwardToJsp = "/errorPage.jsp";
		}		
		
		forwardToPage(request, response, forwardToJsp);
	}
	
	
	private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page){
		
		//Get the request dispatcher object and forward the request to the appropriate JSP page...
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
