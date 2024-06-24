package org.wmg.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.wmg.mapper.MenuMapper;
import org.wmg.security.model.Menu;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    MenuMapper mapper;

    public List<Menu> selectOne(String title){
        return mapper.selectOne(title);
    }

    public Integer deleteOne(String id) {
        return mapper.deleteOne(id);
    }

    public Menu pickOne(String title) {
        return mapper.selectOne(new QueryWrapper<Menu>().like("title",title));
    }
}
