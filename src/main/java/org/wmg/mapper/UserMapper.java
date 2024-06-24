package org.wmg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.wmg.security.model.User;

import java.util.Optional;

/**
 * @author wmg
 * @date 2024/6/24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    @Results(id = "userResult", value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "firstname", column = "FIRSTNAME"),
            @Result(property = "lastname", column = "LASTNAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "activated", column = "ACTIVATED"),
            @Result(property = "authorities", column = "ID",
                    many = @Many(select = "org.wmg.mapper.AuthorityMapper.selectByUserId"))
    })
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    @Select("SELECT * FROM USER WHERE email = #{email}")
    @Results(id = "userResult2", value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "firstname", column = "FIRSTNAME"),
            @Result(property = "lastname", column = "LASTNAME"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "activated", column = "ACTIVATED"),
            @Result(property = "authorities", column = "ID",
                    many = @Many(select = "org.wmg.mapper.AuthorityMapper.selectByUserId"))
    })
    Optional<User> findOneWithAuthoritiesByEmail(String email);
}
