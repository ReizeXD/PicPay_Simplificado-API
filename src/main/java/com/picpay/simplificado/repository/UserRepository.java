package com.picpay.simplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.picpay.simplificado.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
 
}
