package edc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Entrepreneurship Development Cell
 * This Function stores data of new members of EDC club in the lab.edc table
 * @author Samir.Paul
 * @since 24-JAN-2021
 */
public class Enrolment {

	public static void main(String[] args) {
		String URL = "jdbc:mysql://localhost:3306/lab";
		String USER = "root";
		String PASS = "password";
		Connection connection = null;
		String insertQuery = "insert into edc (id, name, mobile, email) values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		int status = 0;
		try {
			connection = DriverManager.getConnection(URL, USER, PASS);
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, 2);
			preparedStatement.setString(2, "Sayan Pal");
			preparedStatement.setString(3, "7319050309");
			preparedStatement.setString(4, "pal.sayan.2002@gmail.com");
			status = preparedStatement.executeUpdate();
			
			if(1 == status) {
				System.out.println("Data Insertion Is Successful.");
			} else {
				System.out.println("Data Insertion Is Failed.");
			}
		} catch (SQLException e) {
			System.out.println("Error: "+e.getMessage());
		} finally {
			if(null != preparedStatement) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println("Error: "+e.getMessage());
				}
			}
			if(null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error: "+e.getMessage());
				}
			}
		}

	}

}

