package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.business.Car;
import com.example.exceptions.DAOException;

public class PublicDAO extends Dao {
	
	public List<Car> getAllVehicles() throws DAOException {

		List<Car> carData = new ArrayList<Car>();
		
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Car car = null;
        try {
            con = this.getConnection();
            
            String query = "SELECT * FROM CAR";
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while (rs.next()) {
            	int carId = rs.getInt("ID");
                String carReg = rs.getString("carReg");
                String carMake = rs.getString("carMake");
                String carModel = rs.getString("carModel");
                String carDate = rs.getString("carDate");
                String image = rs.getString("carImage");
                
                car = new Car();
                car.setCarID(carId);
                car.setCarReg(carReg);
                car.setImagePath(image);
                car.setMake(carMake);
                car.setModel(carModel);
                car.setDate(carDate);
                
                carData.add(car);
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
        return carData;     // u may be null 
    } 

}
