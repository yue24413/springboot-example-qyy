package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import com.example.jdbcexamples.dto.AddressUser;
import com.example.jdbcexamples.dto.UserAddress;
import com.example.jdbcexamples.dto.UserAddress3;
import com.example.jdbcexamples.mapper.UserAddress3ResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Modifying;
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

  // ID即为时间顺序且是索引。效率远高于基于createTime的排序
  @Query("""
        select * from user u
        order by u.id desc 
        limit :#{#pageable.offset}, :#{#pageable.pageSize};#{#
        """)
  List<User> findAllByIdDesc(Pageable pageable);

  // 显式声明映射名称对应DTO中属性名称，避免冲突
  @Query("""
       select a.id as id,a.user_id as user_id,detail,name,a.create_time as create_time,a.update_time as update_time
        from user u join address a on u.id = user_id
        where u.id = :uid
       """)
  List<AddressUser> findAddressUser(String uid);

  @Query(
          value = "select * from user u join address a on u.id = a.user_id where u.id = :uid",
          resultSetExtractorClass = UserAddress3ResultSetExtractor.class
  )
  UserAddress3 findUserAddress3(String uid);
}
