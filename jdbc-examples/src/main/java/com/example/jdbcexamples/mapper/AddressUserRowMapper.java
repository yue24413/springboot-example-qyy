package com.example.jdbcexamples.mapper;


import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dox.User;
import com.example.jdbcexamples.dto.AddressUserDTO;
import org.springframework.jdbc.core.RowMapper;
import lombok.extern.slf4j.Slf4j;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class AddressUserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
       User user = User.builder()
               .id(rs.getString("u.id"))
               .name(rs.getString("name"))
               .createTime(rs.getObject("u.create_time", LocalDateTime.class))
               .updateTime(rs.getObject("u.update_time", LocalDateTime.class))
               .build();
        Address address = Address.builder()
                .id(rs.getString("a.id"))
                .detail(rs.getString("detail"))
                .createTime(rs.getObject("a.create_time", LocalDateTime.class))
                .updateTime(rs.getObject("a.update_time", LocalDateTime.class))
                .build();
        return AddressUserDTO.builder().user(user).address(address).build();
    }
}
