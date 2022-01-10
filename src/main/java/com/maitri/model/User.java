package com.maitri.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/*
 * This class implements the user table
 */
@Entity
@Table(name="user")
public class User {
	 	@Id
	    private String userName;
	   	private String userFirstName;
	    private String userLastName;
	    private String userPassword;
	    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	    @JoinTable(name = "USER_ROLE",
	            joinColumns = {
	                    @JoinColumn(name = "USER_ID")
	            },
	            inverseJoinColumns = {
	                    @JoinColumn(name = "ROLE_ID")
	            }
	    )
	    private Set<Role> role;
	    public User(String userName, String userFirstName, String userLastName, String userPassword, Set<Role> role) {
			super();
			this.userName = userName;
			this.userFirstName = userFirstName;
			this.userLastName = userLastName;
			this.userPassword = userPassword;
			this.role = role;
		}
	    public User() {
	    	
	    }
	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getUserFirstName() {
	        return userFirstName;
	    }

	    public void setUserFirstName(String userFirstName) {
	        this.userFirstName = userFirstName;
	    }

	    public String getUserLastName() {
	        return userLastName;
	    }

	    public void setUserLastName(String userLastName) {
	        this.userLastName = userLastName;
	    }

	    public String getUserPassword() {
	        return userPassword;
	    }

	    public void setUserPassword(String userPassword) {
	        this.userPassword = userPassword;
	    }

	    public Set<Role> getRole() {
	        return role;
	    }

	    public void setRole(Set<Role> role) {
	        this.role = role;
	    }
		@Override
		public String toString() {
			return "User [userName=" + userName + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
					+ ", userPassword=" + userPassword + ", role=" + role + "]";
		}
	    
	
}
