package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.User;
import com.example.jdbcexamples.dto.AddressUserDTO;

import com.example.jdbcexamples.dto.UserAddressDTO;
import com.example.jdbcexamples.dto.UserAddressDTO2;
import com.example.jdbcexamples.mapper.UserAddressResultSetExtractor;
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

  @Query(value = "select * from address a join user u on u.id = a.user_id where u.id =:uid",
          /*指定了一个行映射器类AddressUser2RowMapper，它的任务是从数据库查询的结果集中创建Java对象。*/
          resultSetExtractorClass = UserAddressResultSetExtractor.class)
  UserAddressDTO findUserAddressResultSetExtractorById(String uid);


  //得到姓名和地址个数，按个数由大到小排序

  @Query("""
            select u.name ,COUNT(a.detail) AS address_count 
            from user u left join  address a  on u.id = a.user_id 
            group by u.name 
            order by address_count ASC
        """)
  List<UserAddressDTO2> findUserAddressLenResultSetExtractor();
}
