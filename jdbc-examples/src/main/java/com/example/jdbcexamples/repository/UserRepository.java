package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dox.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  User save(User user);
  User findByname(String name);

  @Query("""
        select * from user limit :offset, :pageSize
        """)
  List<User> findAll(int offset,int pageSize);
}