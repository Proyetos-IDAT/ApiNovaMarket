package com.novo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novo.modelo.User;

@Repository
public interface UserInterface extends JpaRepository<User, Long>{

}
