package com.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.exceptions.DAOException;

public interface Command {
	String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException;
}
