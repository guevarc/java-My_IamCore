package fr.cg.iamcore.exception;

public class DAODeleteException extends Exception {
	
private String deleteFault;
	
	public DAODeleteException(String message) {
		this.deleteFault = message;
	}

	public String getDeleteFault() {
		return deleteFault;
	}


}
