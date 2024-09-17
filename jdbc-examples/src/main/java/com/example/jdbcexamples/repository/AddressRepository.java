package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    @Query("""
            select * from address a
            where a.user_id =:userId  
            """)
    List<Address> findByUserId(String userId);

    @Query("""
        select a.id as id,a.user_id as uid,a.detail as detail,u.name as name,a.create_time as create_time,a.update_time as update_time
        from address a join user u on u.id = a.user_id
        where a.id = :aid
        """)
    List<Address> findAddressUserById(String aid);




    Address save(Address address);
}
