package org.wmg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wmg.security.model.Authority;

import java.util.List;

/**
 * @author wmg
 * @date 2024/6/24
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
    @Select("SELECT * FROM AUTHORITY WHERE NAME IN (SELECT AUTHORITY_NAME FROM USER_AUTHORITY WHERE USER_ID = #{userId})")
    List<Authority> selectByUserId(Long userId);
}
