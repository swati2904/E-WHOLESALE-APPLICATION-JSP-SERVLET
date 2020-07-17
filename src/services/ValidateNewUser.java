package services;

public class ValidateNewUser {

	//--------------------------------VALIDATE THE USER NAME --------------------------------
	public boolean validateUserName(String  username) {
		boolean hasCap = false;
		boolean hasLow = false;
		char c;
		for(int i=0;i<username.length();i++) {
			c = username.charAt(i);
		if(Character.isUpperCase(c)) {
			hasCap = true;
		}
		else if(Character.isLowerCase(c)) {
			hasLow = true;
		}
		if(hasCap && hasLow) {
			return true;
		 	}
		}
		return false;
	}
	
	//-----------------------------VALIDATE THE USER PASSWORD ---------------------------------
	public boolean validatePass(String password) {
		
		boolean hasNum = false;
		boolean hasCap = false;
		boolean hasLow = false;
		char c;
		for(int i=0;i<password.length();i++) {
			c = password.charAt(i);
			if(Character.isDigit(c)) {
				hasNum = true;
			}
			else if(Character.isUpperCase(c)) {
				hasCap = true;
			}
			else if(Character.isLowerCase(c)) {
				hasLow = true;
			}
			if(hasNum && hasCap && hasLow) {
				return true;
			}
		}
		return false;
	}
	
	//----------------------CHECK THE LENGTH OF THE USERNAME -------------------------------
	public boolean lengthUserName(String username) {
		if(username.length() >5) {
			if(validateUserName(username)) {
				return true;
			}
			else {
				System.out.println("Username must contain at least one Uppercase and Lowercase character.");
				return false;
			}
		}else {
			System.out.println("Username must have more than five charaters");
			return false;
		}	
	}

	
	//-----------------------CHECK THE LENGTH OF THE PASSWORD ------------------------------------
	public boolean lengthPass(String password) {
		if(password.length() >6) {
			if(validatePass(password)) {
				return true;
			}
			else {
				System.out.println("Password must contain at least one Upper,Lower case and a Number.");
				return false;
			}
		}else {
			System.out.println("Password must have more than six charaters");
			return false;
		}
	}
	
	//-----------------CHECK THE USERNAME AND PASSWORD FOR THE NEW REGISTRATION----------------------
	public boolean checkUserDetails(String username, String password) {
		boolean name = false;
		boolean pass = false;
		if(validatePass(password)) {
			pass = true;
		}
		if(validateUserName(username)) {
			name = true;
		}
		if(name && pass) {
			return true;
		}
		else {
			return false;
		}
	}
	   
	//----------------------DISPLAYING THE REGISTRATION DETAILS--------------------------------
	public boolean newRegistration(String username, String password){		
		if(checkUserDetails(username,password)) {
			System.out.println("\t \t New user registration successful.");
			System.out.println("\t \t Please enter additional information..");
			System.out.println();
			return true;
		}
		return false;
	}
    
}
