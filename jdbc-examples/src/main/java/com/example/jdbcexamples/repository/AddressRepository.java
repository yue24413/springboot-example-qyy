package com.example.jdbcexamples.repository;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dto.AddressUserDTO;
import com.example.jdbcexamples.mapper.AddressUserRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    Address save(Address address);
    @Query("""
            select * from address a
            where a.user_id =:userId  
            """)
    List<Address> findByUserId(String userId);

    @Query(value = "select * from address a join user u on u.id = a.user_id where a.id=:aid",
            /*指定了一个行映射器类AddressUser2RowMapper，它的任务是从数据库查询的结果集中创建Java对象。*/
            rowMapperClass = AddressUserRowMapper.class)
    AddressUserDTO findAddressUserDTOById(String aid);

}
