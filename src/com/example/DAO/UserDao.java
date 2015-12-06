package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.business.Car;
import com.example.business.User;
import com.example.exceptions.DAOException;

public class UserDao extends Dao {

    public User findUserByUsernamePassword(String uname, String pword) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);
            
            rs = ps.executeQuery();
            if (rs.next()) {
            	int userId = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");
                u = new User(userId, firstname, lastname, username, password);
            }
        } catch (SQLException e) {
            throw new DAOException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                   this.freeConnection((com.mysql.jdbc.Connection) con);
                }
            } catch (SQLException e) {
                throw new DAOException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return u;     // u may be null 
    } 
    
    
    public boolean insertNewVehicle(Car car) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.getConnection();
            
            // INSERT INTO car VALUES (null, "05DL11803", "BMW", "118D", "20/04/2015",""),
            String query = "INSERT INTO car VALUES (null, ?, ?, ?, ?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, car.getCarReg());
            ps.setString(2, car.getMake());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getDate());
            ps.setString(5,"");
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
            	if(ps == null) {
            		return false;
            	}
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                	this.freeConnection((com.mysql.jdbc.Connection) con);
                }
            } catch (SQLException e) {
                throw new DAOException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return true;  
    } 
    
    public boolean updateVehicle(Car car) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.getConnection();
            
            String query = "UPDATE car SET carReg = ?, carMake = ?, carModel = ?, carDate = ? WHERE ID = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, car.getCarReg());
            ps.setString(2, car.getMake());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getDate());
            ps.setInt(5,car.getCarID());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DAOException("findUserByUsernamePassword " + e.getMessage());
        } finally {
            try {
            	if(ps == null) {
            		return false;
            	}
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                	this.freeConnection((com.mysql.jdbc.Connection) con);
                }
            } catch (SQLException e) {
                throw new DAOException("findUserByUsernamePassword" + e.getMessage());
            }
        }
        return true;  
    } 
}
