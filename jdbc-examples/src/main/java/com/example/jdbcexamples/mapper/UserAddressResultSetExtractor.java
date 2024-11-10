package com.example.jdbcexamples.mapper;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dox.User;
import com.example.jdbcexamples.dto.UserAddressDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class UserAddressResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
       User user = null;
        List<Address> addresses = new ArrayList<Address>();
        while (rs.next()) {
            if(user == null) {
                user = User.builder()
                        .id(rs.getString("u.id"))
                        .name(rs.getString("name"))
                        .createTime(rs.getObject("u.create_time", LocalDateTime.class))
                        .updateTime(rs.getObject("u.update_time", LocalDateTime.class))
                        .build();
            }
            Address address = Address.builder()
                    .id(rs.getString("a.id"))
                    .detail(rs.getString("detail"))
                    .createTime(rs.getObject("a.create_time", LocalDateTime.class))
                    .updateTime(rs.getObject("a.update_time", LocalDateTime.class))
                    .build();
            addresses.add(address);
        }
        return UserAddressDTO.builder().user(user).addresses(addresses).build();
    }
}
