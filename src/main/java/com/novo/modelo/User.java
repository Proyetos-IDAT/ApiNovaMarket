package com.novo.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {

	private @Id @GeneratedValue long id;
	private @NotBlank String userName;
	private @NotBlank String password;
	
	public User() {
		
	}

	public User(@NotBlank String userName, @NotBlank String password) {
		this.userName = userName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "User {id=" + id + ", userName='" + userName + "', password='" + password + "'}";
	}	
}
