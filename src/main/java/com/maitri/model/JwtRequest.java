package com.maitri.model;
/*
 * This class captures the username and password after login.
 */
public class JwtRequest {
	private String userName;
    private String userPassword;
    public JwtRequest() {
    	
    }
    
    public JwtRequest(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

	@Override
	public String toString() {
		return "JwtRequest [userName=" + userName + ", userPassword=" + userPassword + "]";
	}
    
}
