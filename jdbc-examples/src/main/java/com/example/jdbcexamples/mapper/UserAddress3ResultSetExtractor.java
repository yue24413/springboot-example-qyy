package com.example.jdbcexamples.mapper;

import com.example.jdbcexamples.dox.Address;
import com.example.jdbcexamples.dto.UserAddress3;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserAddress3ResultSetExtractor implements ResultSetExtractor<UserAddress3> {
    @Override
    public UserAddress3 extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Address> addressList = new ArrayList<Address>();
        UserAddress3.UserAddress3Builder builder = UserAddress3.builder();
        //ResultSet 的游标（cursor）最初指向的是结果集之前的空白位置，而不是第一行数据。
        //rs.next() 方法必须在访问任何字段之前调用，尝试在 rs.next() 之前访问字段，会导致 Before start of result set 的异常。
        while (rs.next()) {
            builder.id(rs.getString("user_id"));
            builder.name(rs.getString("name"));
            builder.createTime(rs.getObject("u.create_time", LocalDateTime.class));
            builder.updateTime(rs.getObject("u.update_time", LocalDateTime.class));
            Address address = Address.builder()
                .id(rs.getString("id"))
                .detail(rs.getString("detail"))
                .createTime(rs.getObject("u.create_time", LocalDateTime.class))
                .updateTime(rs.getObject("u.update_time", LocalDateTime.class))
                .build();
            addressList.add(address);
        }
        return builder.addresses(addressList).build();
    }
}
        /*
        UserAddress3.UserAddress3Builder builder = UserAddress3.builder(); 主要目的是创建一个 UserAddress3Builder 实例。
        UserAddress3 userAddress3 = UserAddress3.builder().build(); 目的是创建一个 UserAddress3 对象。
        创建的 UserAddress3Builder 实例通常会在设置完所有需要的属性之后调用 build() 方法来创建 UserAddress3 对象。
        UserAddress3 对象则是一个最终产品，可以直接使用。
        在设置过程中中断构建过程，而不立即创建最终对象。创建一个 UserAddress3Builder 实例可以在任何时候停止设置，而不会创建一个部分完成的对象。
        * */
