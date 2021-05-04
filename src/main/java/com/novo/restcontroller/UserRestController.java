package com.novo.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novo.interfaces.UserInterface;
import com.novo.modelo.User;

@RestController
public class UserRestController {

	//Llamamos a la interface para usar el JpaRepository
	@Autowired
	UserInterface ur;
	//Mandamos via post para que se registre también se va a agregar
	//La ruta 
	@PostMapping("/api/register")
	public String register(@Valid @RequestBody User user) {
		List<User>users=ur.findAll();
		System.out.println("New User -> " + user);
		//va a recorrer usuarios
		for(User u:users) {
			if(user.equals(u)) {
				System.out.println("User already exists!");
				return "USER_ALREADY_EXISTS";
			}
		}
		//se va a guardar el usuario registrador
		ur.save(user);
		return "SUCCESS";
	}
	@PostMapping("/api/login")
	public String login(@Valid @RequestBody User user) {
		List<User>users=ur.findAll();
		for(User u:users) {
			if(u.equals(user)) {
				return user.getUserName();
			}
		}
		return "FAILURE";
	}

}
