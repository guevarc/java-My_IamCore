/**
 * 
 */
package fr.cg.iamcore.exception;

/**
 * @author tbrou
 *
 */
public class DAOUpdateException extends Exception {

private String updateFault;
	
	public DAOUpdateException(String message) {
		this.updateFault = message;
	}

	public String getupdateFault() {
		return updateFault;
	}


	
}
