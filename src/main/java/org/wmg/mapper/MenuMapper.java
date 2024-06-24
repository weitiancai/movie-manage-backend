package org.wmg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wmg.security.model.Menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectOne(@Param("title") String title);

    Menu selectFid(Integer fid);//通过id查询

    Integer deleteOne(@Param("id") String id);

    Page<Menu> selectByMenuPage(Page<?> page, @Param("title") String title);

    Menu selectByMyId(long l);

    Optional<Integer> existsItem(@Param("title") String title);

    List<Map<String,Object>> selectAnyTable(@Param("tableName") String tableName, @Param("columnName") String columnName, @Param("likeValue") String likeValue);
}
