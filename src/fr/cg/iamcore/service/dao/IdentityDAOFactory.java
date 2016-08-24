/**
 * 
 */
package fr.cg.iamcore.service.dao;

import fr.cg.iamcore.exception.DAOInitializationException;

/**
 * @author tbrou
 *
 */
public class IdentityDAOFactory {
	
	
	public static IdentityDAOInterface getIdentityDAO() throws DAOInitializationException{
		return IdentityJDBCDAO.getInstance();
	}

}
