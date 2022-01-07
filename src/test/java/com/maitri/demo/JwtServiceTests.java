package com.maitri.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.maitri.dao.UserDao;
import com.maitri.model.User;
import com.maitri.service.UserService;
/*
 * This test class tests the functions created in UserService class.
 */
@SpringBootTest
public class JwtServiceTests {
	@Autowired
	private UserService jwtUserDetailService;
	@Mock
	private UserDao userRepository;

	@Test
	public void addUserTest() {
		User user = new User("maitrisheth", "maitri", "sheth", "maitrisheth", null);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user.getUserName(), jwtUserDetailService.registerNewUser(user).getUserName());
	}

	@Test
	public void updateUserTest() {
		User user1 = new User();
		user1.setUserLastName("shah");
		user1.setUserFirstName("ami");
		user1.setRole(null);
		user1.setUserPassword("amishah");
		User user2 = jwtUserDetailService.updateUser("amisheth", user1);
		if (user2.getUserLastName().equals("shah")) {

		} else {
			Assertions.fail("failed");
		}
	}

}
