package com.maitri.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * This class implements the role table
 */
@Entity
@Table(name="role")
public class Role {
		@Id
	    private String roleName;
	    private String roleDescription;
	    public Role() {
	    	
	    }
	    
	    public Role(String roleName, String roleDescription) {
			super();
			this.roleName = roleName;
			this.roleDescription = roleDescription;
		}
	    
		public String getRoleName() {
	        return roleName;
	    }

	    public void setRoleName(String roleName) {
	        this.roleName = roleName;
	    }

	    public String getRoleDescription() {
	        return roleDescription;
	    }

	    public void setRoleDescription(String roleDescription) {
	        this.roleDescription = roleDescription;
	    }

		@Override
		public String toString() {
			return "Role [roleName=" + roleName + ", roleDescription=" + roleDescription + "]";
		}
	    
	}
