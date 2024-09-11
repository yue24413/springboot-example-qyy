package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dox.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query("""
select a.* from user u,address a where u.id=a.user_id and u.id=:uid
""")
    List<Address> findAddressesById(String uid);

    @Query("""
select * from user u limit :offset, :pageSize
""")
    List<User> findAll(int offset, int pageSize);

@Query("""
select * from user u limit :#{#pageable.offset},:#{#pageable.pageSize}
""")
List<User> findAll(Pageable pageable);

@Query("""
select * from user u order by u.id desc
limit :#{#pageable.offset}, :#{#pageable.pageSize}
""")
    List<User> findByIdDesc(Pageable pageable);

}
