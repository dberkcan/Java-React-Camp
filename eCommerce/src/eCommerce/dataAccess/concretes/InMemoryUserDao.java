package eCommerce.dataAccess.concretes;

import java.util.List;
import java.util.ArrayList;

import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class InMemoryUserDao implements UserDao {
	
	private List<User> users = new ArrayList<User>();
	
	public InMemoryUserDao() {
		super();
		User user1 = new User(1,"Ali","Yal��n","aliyalcin@gmail.com","123456",true);
		User user2 = new User(2,"Enes","Celep","enescelep@gmail.com","741852",true);
		User user3 = new User(3,"Galip","Do�an","galipdogan@gmail.com","852963",true);
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}

	@Override
	public void add(User user) {
		System.out.println("Log inmemory: " + user.getePosta());
		users.add(user);
		
	}

	@Override
	public void update(User user) {
		User userToUpdate = users.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);
		
	}

	@Override
	public void delete(int userId) {
		User userToDelete = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
		
		users.remove(userToDelete);
		
	}

	@Override
	public User get(String email) {
		User user = users.stream().filter(u -> u.getePosta() == email).findFirst().orElse(null);
		return user;
	}

	@Override
	public List<User> getAll() {
		return users;
	}

}
