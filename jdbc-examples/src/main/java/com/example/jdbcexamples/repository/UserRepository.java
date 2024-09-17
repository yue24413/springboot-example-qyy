package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import com.example.jdbcexamples.dto.AddressUser;
import com.example.jdbcexamples.dto.UserAddress;
import com.example.jdbcexamples.dto.UserAddress3;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  User save(User user);
  User findByname(String name);

  @Query("""
        select * from user limit :offset, :pageSize
        """)
  List<User> findAll(int offset,int pageSize);

  @Query("""
       select * from user u
        limit :#{#pageable.offset}, :#{#pageable.pageSize}
       """)
  List<User> findAll_2(Pageable pageable);

  @Query("""
        select * from user u
        order by u.id desc 
        limit :#{#pageable.offset}, :#{#pageable.pageSize};#{#
        """)
  List<User> findAllByIdDesc(Pageable pageable);


  @Query("""
       select a.id as id,a.user_id as user_id,detail,name,a.create_time as create_time,a.update_time as update_time
        from user u join address a on u.id = address.user_id
        where u.id = :uid
       """)
  List<AddressUser> findAddressUser(String uid);

  UserAddress findAddressUserByUid(String uid);
}
