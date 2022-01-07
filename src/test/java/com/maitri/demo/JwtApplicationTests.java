package com.maitri.demo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.maitri.dao.UserDao;
import com.maitri.model.User;

/*
 * This class test the CRUD functionality of JPA Repository.
*/
@SpringBootTest
public class JwtApplicationTests {
	@Autowired
	private UserDao userRepository;
	/*
	 * Test the create functionality of JPA repository
	 */
	@Test
	public void saveUserTest() {
		User expectedUser = new User();
		expectedUser.setUserName("maitrisheth");
		expectedUser.setUserFirstName("maitri");
		expectedUser.setUserLastName("sheth");
		expectedUser.setUserPassword("123");
		User user1 = userRepository.save(expectedUser);
		assertThat(user1.getUserName()).isEqualTo("maitrisheth");
	}
	/*
	 * Test the read functionality of JPA repository
	 */
	@Test
	public void getUserTest() {
		User user = userRepository.findById("maitrisheth").get();
		assertThat(user.getUserName()).isEqualTo("maitrisheth");
	}
	/*
	 * Test the findAll() function of JPA repository
	 */
	@Test
	public void getListOfUsersTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isGreaterThan(0);
	}
	/*
	 * Test the update() function of JPA repository
	 */
	@Test
	public void updateUserTest() {
		User expectedUser = new User();
		expectedUser.setUserName("maitrisheth1");
		expectedUser.setUserFirstName("maitri");
		expectedUser.setUserLastName("sheth");
		expectedUser.setUserPassword("123");
		userRepository.save(expectedUser);
		User userUpdated = userRepository.save(expectedUser);
		assertThat(userUpdated.getUserName()).isEqualTo("maitrisheth1");

	}
	/*
	 * This testcase is created to test weather delete function works properly or not.
	 */
	@Test
	public void deleteUserTest() {
		User expectedUser = new User();
		expectedUser.setUserName("maitrisheth1");
		expectedUser.setUserFirstName("maitri");
		expectedUser.setUserLastName("sheth");
		expectedUser.setUserPassword("123");
		userRepository.delete(expectedUser);
		User user1 = null;
		Optional<User> optionalUser = userRepository.findById("maitrisheth1");
		if (optionalUser.isPresent()) {
			user1 = optionalUser.get();
			assertThat(user1).isNull();
		}
	}
}
