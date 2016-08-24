package fr.cg.iamcore.service.dao;

import java.util.List;

import fr.cg.iamcore.datamodel.Identity;
import fr.cg.iamcore.exception.DAOSaveException;
import fr.cg.iamcore.exception.DAOSearchException;
import fr.cg.iamcore.exception.DAOUpdateException;

public interface IdentityDAOInterface {
	
	public void save(Identity identity) throws DAOSaveException ;
	
	public List<Identity> search(Identity criteria) throws DAOSearchException ;
	
	public void update(Identity identity) throws DAOUpdateException;
	
	public void delete(Identity identity) throws DAODeleteException ;
	
	public void releaseResources() throws DAOResourceException;
	

}
