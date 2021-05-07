package eCommerce.business.concretes;

import java.util.List;

import eCommerce.business.abstracts.AuthService;
import eCommerce.business.abstracts.UserService;
import eCommerce.core.utils.Utils;
import eCommerce.entities.concretes.LoginDto;
import eCommerce.entities.concretes.User;

public class AuthManager implements AuthService {
	
	private UserService userService;
	
	public AuthManager(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public void register(User user) {
		if(userValidate(user) && passwordValidate(user.getPassword()) && userExists(user.getePosta()) == false && Utils.emailValidate(user.getePosta())) {
			userService.add(user);
		}else {
			System.out.println("Kay�t ba�ar�s�z!");
		}
		
	}

	@Override
	public void verify(User user, String token) {
		if(user != null && token.length() >15) {
			User existUser = userService.get(user.getePosta());
			existUser.setVerify(true);
			
			userService.update(existUser);
			
			System.out.println("Email do�ruland�.");
		}else {
			System.out.println("Email do�rulanamad�.");
		}
		
	}

	@Override
	public boolean userExists(String email) {
		User user = userService.get(email);
		
		if(user == null) {
			return false;
		}else {
			System.out.println("Email zaten mevcut! " + email);
			return true;
		}
	}

	@Override
	public void login(LoginDto dto) {
		User user = userService.get(dto.getEmail());
		
		if(user != null && user.getPassword().equals(dto.getPassword())) {
			System.out.println("Ba�ar�yla giri� yapt�n�z.");
		}else {
			System.out.println("Kullan�c� ad� veya �ifre yanl��!");
		}
		
	}
	
	public boolean userValidate(User user) {
		if(user != null && !user.getName().isEmpty() && !user.getLastName().isEmpty() && !user.getePosta().isEmpty() && !user.getPassword().isEmpty()) {
			return true;
		}
		
		System.out.println("Bo� alan b�rakmay�n�z!");
		return false;
	}
	
	public boolean passwordValidate(String password) {
		if(password.length() >=6 ) {
			return true;
		}
		
		System.out.println("�ifreniz 6 karakterden daha uzun olmal�d�r.");
		return false;
	}
	
	
	
	
	
	
	
	

}
