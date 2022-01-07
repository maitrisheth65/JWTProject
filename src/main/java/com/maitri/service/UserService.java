package com.maitri.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.maitri.dao.RoleDao;
import com.maitri.dao.UserDao;
import com.maitri.model.Role;
import com.maitri.model.User;
/*
 * This class implements the CRUD functionality for User table.
 */
@Service
public class UserService {
	//Create objects of different classses which are loosely coupled(Autowired).
		@Autowired
	    private UserDao userDao;
	    @Autowired
	    private RoleDao roleDao;
	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    //While creating role table,creates two roles(Admin & User).
	    
	    public void initRoleAndUser() {

	        Role adminRole = new Role();
	        adminRole.setRoleName("Admin");
	        adminRole.setRoleDescription("Admin role");
	        roleDao.save(adminRole);

	        Role userRole = new Role();
	        userRole.setRoleName("User");
	        userRole.setRoleDescription("Default role for newly created record");
	        roleDao.save(userRole);

	        //Adding admin credentials at runtime.
	        
	        User adminUser = new User();
	        adminUser.setUserName("admin123");
	        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
	        adminUser.setUserFirstName("admin");
	        adminUser.setUserLastName("admin");
	        Set<Role> adminRoles = new HashSet<>();
	        adminRoles.add(adminRole);
	        adminUser.setRole(adminRoles);
	        userDao.save(adminUser);
	    }
	    
	    //Creates New Users

	    public User registerNewUser(User user) {
	        Role role = roleDao.findById("User").get();
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);
	        user.setRole(userRoles);
	        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
	        return userDao.save(user);
	    }

	    //Encode password into BCryptEncode.
	    
	    public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }

	    //Find all the registered users.
	    
	    public List<User> findAll(){
			   return userDao.findAll();
		   }
	    
	    //Delete User By its id.

		public void deleteByUsername(String userName) {
			userDao.deleteById(userName);
		}
		
		//Update user details

		public User updateUser(String id, User user) {
			  User updatedContact = userDao.findById(id).orElse(null);
		      updatedContact.setUserFirstName(user.getUserFirstName()); 
		      updatedContact.setUserLastName(user.getUserLastName());
		      updatedContact.setRole(user.getRole());
			  updatedContact.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		      return userDao.save(updatedContact);
		 }
		
		//Find particular User.
		
		public Optional<User> findById(String id) {
		        return userDao.findById(id);
		    }
		}
