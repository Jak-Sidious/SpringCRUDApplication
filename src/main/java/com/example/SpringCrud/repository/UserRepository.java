package com.example.SpringCrud.repository;

import com.example.SpringCrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
