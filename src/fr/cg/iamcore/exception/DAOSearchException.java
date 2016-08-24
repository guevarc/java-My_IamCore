/**
 * 
 */
package fr.cg.iamcore.exception;

/**
 * @author tbrou
 *
 */
public class DAOSearchException extends Exception {
	
	private String searchFault;
	
	public DAOSearchException(String message) {
		this.searchFault = message;
	}

	public String getSearchFault() {
		return searchFault;
	}

}
