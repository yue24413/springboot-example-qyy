package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  User save(User user);
  User findById(int id);
}