package fr.cg.iamcore.service.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.cg.iamcore.exception.DAOInitializationException;

/**
 * This class implements the authentication service
 */
public class AuthenticationService {
	
	private static final String COLUMN_USERS_USERNAME = "USERS_USERNAME";
	private static final String COLUMN_USERS_PASSWORD = "USERS_PASSWORD";
	private static final String CONF_USERNAME = "tom";
	private static final String CONF_PASSWORD = "tom";
	private static final String CONF_CONNECTION_STRING = "jdbc:derby://localhost:1527/Identities;create=true";
	//private static final String QUERY_AUTHENTICATE_IDENTITY = "SELECT * FROM USERS WHERE USERS_USERNAME=? and USERS_PASSWORD=?";
	//private static final String QUERY_UPDATE_IDENTITY = 
	//"SELECT * FROM USERS WHERE USERS_USERNAME='" + username + "' and USERS_PASSWORD='" + password + "'";
	
	String databaseUsername;
	String databasePassword;

	/**
	 * This class implements the authentication service
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean authenticate(String username, String password) throws SQLException {
	
		Connection connection = DriverManager.getConnection(CONF_CONNECTION_STRING,
				CONF_USERNAME, CONF_PASSWORD);
		
		PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USERS_USERNAME='" + username + "' and USERS_PASSWORD='" + password + "'");
		//PreparedStatement prepareStatement = connection.prepareStatement(QUERY_AUTHENTICATE_IDENTITY);
		//prepareStatement.setString(1, criteria.getDisplayName());
		//prepareStatement.setString(2, criteria.getEmail());
	    ResultSet rs = prepareStatement.executeQuery();
		while (rs.next()) {
			// Check Username and Password
	  		databaseUsername = rs.getString(COLUMN_USERS_USERNAME);
	  		databasePassword = rs.getString(COLUMN_USERS_PASSWORD);  		  		
		}
		
		connection.close();	 
		
		if (username.equals(databaseUsername) && password.equals(databasePassword)) {
	        System.out.println("Successful Login!\n----");
	        return true;
	    } else {
	        System.out.println("Incorrect Password\n----");
	        return false;
	    }
	}
}
