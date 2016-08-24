package fr.cg.iamcore.service.dao;

public class DAODeleteException extends Exception {

	private String deleteFault;
	
	public DAODeleteException(String message) {
		this.setDeleteFault(message);
	}

	public String getDeleteFault() {
		return deleteFault;
	}

	public void setDeleteFault(String deleteFault) {
		this.deleteFault = deleteFault;
	}


}
