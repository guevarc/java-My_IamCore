/**
 * 
 */
package fr.cg.iamcore.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

import fr.cg.iamcore.service.authentication.AuthenticationService;
import fr.cg.iamcore.service.dao.DAODeleteException;
import fr.cg.iamcore.service.dao.DAOResourceException;
import fr.cg.iamcore.service.dao.IdentityJDBCDAO;
import fr.cg.iamcore.datamodel.Identity;
import fr.cg.iamcore.exception.DAOInitializationException;
import fr.cg.iamcore.exception.DAOSaveException;
import fr.cg.iamcore.exception.DAOSearchException;
import fr.cg.iamcore.exception.DAOUpdateException;





public class Application2 {
	
	private static final Logger logger =  LogManager.getLogger(Application2.class);

	/**
	 * Main Class
	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args) throws SQLException, DAOSaveException, DAOInitializationException, DAOSearchException, DAOUpdateException, DAODeleteException, DAOResourceException {
		IdentityJDBCDAO dao = IdentityJDBCDAO.getInstance();
		
		logger.info("program started");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the IAM System");
		

		System.out.println("Please enter your username: ");
		String username = scanner.nextLine();
		
		
		System.out.println("Please enter your password: ");
		String password = scanner.nextLine();
	
		
		logger.info("program received this input : user = {}, password ={} ", username, 
				(password == null ) ? "null" : "****");
		
		AuthenticationService authService = new AuthenticationService();
		if (!authService.authenticate(username, password)) {
			System.out.println("The provided credentials are wrong");
			scanner.close();
			return;
		}
	
		String menuAnswer = "0";
		
		//Loop on the main menu
		do {
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Menu for the IAM application :");
		System.out.println("1 - Create an Identity");
		System.out.println("2 - Search for an Identity");
		System.out.println("3 - Update an Identity");
		System.out.println("4 - Delete an Identity");
		System.out.println("5 - Exit");
		System.out.print("your choice (1|2|3|4|5) : ");
		String input = scanner.nextLine();
		menuAnswer = input;
		
		
		System.out.println(" ");
		switch (menuAnswer) {
		case "1":
			System.out.println("Creation Activity");
			System.out.println("Please enter the Identity display name: ");
			String displayName = scanner.nextLine();
			System.out.println("Please enter the Identity email address: ");
			String email = scanner.nextLine();
			Identity identity = new Identity(displayName, email, null);

			//Save the Identity and then do a search on it to confirm the save activity
			try {
				dao.save(identity);
				System.out.println("The identity has been added successfully ");
				List<Identity> id = dao.search(identity);
				System.out.println(id);
				dao.releaseResources();	
			} catch (DAOSaveException e) {
				System.out.println(e.getSaveFault());
			}
			

			break;
		case "2":
			System.out.println("Search Activity");
			System.out.println("Please enter the Identity display name to Search: ");
			String searchName = scanner.nextLine();
			System.out.println("Please enter the Identity email address to Search: ");
			String searchEmail = scanner.nextLine();
			Identity searchIdentity = new Identity(searchName, searchEmail, null);
		
			// Search for the Identity
			try {
				List<Identity> identities = dao.search(searchIdentity);
				if (identities.equals("")){
					System.out.println("The Identity doesn't exit in the database");
				}else{
				System.out.println(identities);
				}				
			} catch (DAOSearchException e) {
				System.out.println(e.getSearchFault());
			}	
			break;

		case "3":
			
			System.out.println("Modification Activity");
			// Prompt the user to type the name and email of the current Identity
			System.out.println("Please enter the display name to modify: ");
			String currentName = scanner.nextLine();
			System.out.println("Please enter the email to modify: ");
			String currentEmail = scanner.nextLine();
			
			Identity currentIdentity = new Identity(currentName, currentEmail, null);

			// First do a search to make sure the Identity is the in the database
			try {
				System.out.println("Current identities with the requested name and email: ");
				System.out.println(" ");
				List<Identity> identities = dao.search(currentIdentity);
				System.out.println(identities);
			} catch (DAOSearchException e) {
				System.out.println(e.getSearchFault());
			}
			// Then, prompt the user again for the new name and email and update the Identity
			// Search again to display the changes made
			try {
				System.out.println(" ");
				System.out.println("From the existing options shown above please enter the name, email and uid to modify ");
				System.out.println(" ");
				System.out.println("Enter the Identity name to modify: ");
				String modifyName = scanner.nextLine();
				System.out.println("Enter the Identity email address to modify: ");
				String modifyEmail = scanner.nextLine();
				System.out.println("Enter the Identity email uid to modify: ");
				String modifyuid = scanner.nextLine();
				Identity modifyIdentity = new Identity(modifyName, modifyEmail, modifyuid);
				dao.update(modifyIdentity);
				System.out.println("After modifying the identity: ");
				System.out.println(" ");
				List<Identity> identities = dao.search(modifyIdentity);
				System.out.println(identities);
				dao.releaseResources();
			} catch (DAOUpdateException e) {
				System.out.println(e.getupdateFault());
			}
			
			break;

		case "4":
			System.out.println("Deletion Activity");
			
			// First do a search to make sure the Identity is the in the database
			// Then, prompt the user for the Uid as a confirmation, then delete the Identity
			// Search again to confirm the deletion took place
						
			try {
				System.out.println("Please enter the identity name to delete: ");
				String deleteName = scanner.nextLine();
				System.out.println("Please enter the identity email to delete: ");
				String deleteEmail = scanner.nextLine();
				Identity deleteIdentity = new Identity(deleteName, deleteEmail, null);
				System.out.println("Before delete: ");
				List<Identity> identities = dao.search(deleteIdentity);
				System.out.println(identities);
				System.out.println("Confirm that you want to delete this identity by typing the uid: ");
				String deleteUid = scanner.nextLine();
				Identity deleteIdentity2 = new Identity(deleteName, deleteEmail, deleteUid);
				dao.delete(deleteIdentity2);
				System.out.println("The identity has been deleted: ");
				identities = dao.search(deleteIdentity);
				System.out.println(identities);
				dao.releaseResources();
				
			} catch (DAODeleteException e) {
				System.out.println(e.getDeleteFault());
			}
			break;
			
		case "5":
			System.out.println("The program will now exit");
			scanner.close();
			dao.releaseResources();
			return;

		default:
			System.out.println("Invalid choice.");
			break;
		}
		
		}while (menuAnswer != "5");
		
		scanner.close();
	}
}

		
	


